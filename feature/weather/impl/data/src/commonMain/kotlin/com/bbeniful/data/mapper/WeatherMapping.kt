package com.bbeniful.data.mapper

import com.bbeniful.core.domain.model.WeatherFront
import com.bbeniful.data.model.CurrentDto
import com.bbeniful.data.model.DailyDto
import com.bbeniful.data.model.HourlyDto
import com.bbeniful.data.model.OpenMeteoResponse
import com.bbeniful.core.domain.model.CurrentWeather
import com.bbeniful.core.domain.model.DailyWeather
import com.bbeniful.core.domain.model.Weather
import kotlin.math.abs

fun OpenMeteoResponse.toDomain(): Weather {
    val currentWeather = current.toDomain(hourly = hourly)
    val dailyForecast = daily.toDomainForecast(hourly = hourly)

    return Weather(
        current = currentWeather,
        tomorrow = dailyForecast.getOrNull(1)
            ?: dailyForecast.firstOrNull()
            ?: throw IllegalStateException("Daily forecast is missing."),
        weeklyForecast = dailyForecast
    )
}

fun CurrentDto.toDomain(hourly: HourlyDto): CurrentWeather {
    val weatherFront = hourly.calculateWeatherFront(currentTime = time)

    return CurrentWeather(
        temperatureCelsius = temperature_2m,
        apparentTemperatureCelsius = apparent_temperature,
        weatherCode = weather_code,
        humidityPercent = null,
        windSpeedKmH = wind_speed_10m,
        pressureHpa = pressure_msl,
        weatherFront = weatherFront,
        description = weather_code.toWeatherDescription(),
        observationTimeIso = time
    )
}

fun DailyDto.toDomainForecast(hourly: HourlyDto): List<DailyWeather> {
    return time.indices.map { index ->
        val date = time[index]
        DailyWeather(
            dateIso = date,
            minTemperatureCelsius = temperature_2m_min[index],
            maxTemperatureCelsius = temperature_2m_max[index],
            weatherCode = weather_code[index],
            pressureHpa = hourly.averagePressureForDate(date),
            weatherFront = hourly.calculateWeatherFrontForDate(date),
            description = weather_code[index].toWeatherDescription()
        )
    }
}

private fun List<Double>.maxChangeInWindow(windowSize: Int): Double {
    if (size < windowSize) return 0.0
    return windowed(windowSize).maxOf { window ->
        abs(window.last() - window.first())
    }
}

private fun HourlyDto.valuesForDate(
    dateIso: String,
    source: List<Double>
): List<Double> {
    return time.indices
        .filter { time[it].startsWith(dateIso) }
        .mapNotNull { source.getOrNull(it) }
}

fun HourlyDto.calculateWeatherFrontForDate(dateIso: String): WeatherFront {
    val pressures = valuesForDate(dateIso, pressure_msl)
    val temps = valuesForDate(dateIso, temperature_2m)
    val winds = valuesForDate(dateIso, wind_speed_10m)
    val clouds = valuesForDate(dateIso, cloud_cover)

    if (pressures.size < 4) return WeatherFront.None

    return compositeScore(pressures, temps, winds, clouds)
}

fun HourlyDto.calculateWeatherFront(
    currentTime: String
): WeatherFront {
    val currentIndex = time.indexOfLast {
        it.startsWith(currentTime.substringBefore(":"))
    }
    if (currentIndex == -1) return WeatherFront.None

    val rangeStart = (currentIndex - 12).coerceAtLeast(0)
    val range = rangeStart..currentIndex

    val pressures = range.mapNotNull { pressure_msl.getOrNull(it) }
    val temps = range.mapNotNull { temperature_2m.getOrNull(it) }
    val winds = range.mapNotNull { wind_speed_10m.getOrNull(it) }
    val clouds = range.mapNotNull { cloud_cover.getOrNull(it) }

    if (pressures.size < 4) {
        val todayIso = currentTime.substringBefore("T")
        println("DEBUG fallback to date: $todayIso")
        val result = calculateWeatherFrontForDate(todayIso)
        println("DEBUG fallback result: $result")
        return result
    }

    return compositeScore(pressures, temps, winds, clouds)
}


private fun compositeScore(
    pressures: List<Double>,
    temps: List<Double>,
    winds: List<Double>,
    clouds: List<Double>
): WeatherFront {
/*
    println("DEBUG compositeScore: p=${pressures.size} t=${temps.size} w=${winds.size} c=${clouds.size}")
    println("DEBUG clouds=$clouds")*/
    val pressure3h = pressures.maxChangeInWindow(4)
    val pressure6h = pressures.maxChangeInWindow(7)
    val tempMaxJump = temps.maxHourlyJump()
    val windMaxJump = winds.maxHourlyJump()
    val cloud3h = clouds.maxChangeInWindow(4)
    val cloud6h = clouds.maxChangeInWindow(7)

    var score = 0.0

    val pressureScore = when {
        pressure3h >= 4.0 || pressure6h >= 8.0 -> 3.0
        pressure3h >= 2.5 || pressure6h >= 5.0 -> 2.0
        pressure3h >= 1.0 || pressure6h >= 2.0 -> 1.0
        else -> 0.0
    }
    score += pressureScore

    val tempScore = when {
        tempMaxJump >= 3.5 -> 2.0
        tempMaxJump >= 2.5 -> 1.5
        tempMaxJump >= 1.5 -> 0.5
        else -> 0.0
    }
    score += tempScore

    score += when {
        windMaxJump >= 10.0 -> 2.0
        windMaxJump >= 7.0  -> 1.5
        windMaxJump >= 5.0  -> 0.5
        else -> 0.0
    }

    val hasPrimarySignal = pressureScore >= 1.0 && tempScore >= 1.5
    score += if (hasPrimarySignal) {
        when {
            cloud3h >= 60.0 || cloud6h >= 70.0 -> 1.5
            cloud3h >= 40.0 || cloud6h >= 50.0 -> 1.0
            cloud3h >= 25.0 || cloud6h >= 30.0 -> 0.5
            else -> 0.0
        }
    } else {
        if (cloud3h >= 60.0 || cloud6h >= 70.0) 0.5 else 0.0
    }

    return score.toWeatherFront()
}

private fun List<Double>.maxHourlyJump(): Double {
    if (size < 2) return 0.0
    return zipWithNext { a, b -> abs(b - a) }.max()
}

private fun Double.toWeatherFront(): WeatherFront {
    return when {
        this >= 6.0 -> WeatherFront.Severe
        this >= 4.0 -> WeatherFront.Strong
        this >= 2.5 -> WeatherFront.Moderate
        this >= 1.0 -> WeatherFront.Weak
        else -> WeatherFront.None
    }
}

fun HourlyDto.averagePressureForDate(dateIso: String): Double? {
    val pressures = valuesForDate(dateIso, pressure_msl)
    if (pressures.isEmpty()) return null
    return pressures.average()
}

fun Int?.toWeatherDescription(): String {
    return when (this) {
        0 -> "Clear sky"
        1, 2, 3 -> "Partly cloudy"
        45, 48 -> "Fog"
        51, 53, 55 -> "Drizzle"
        56, 57 -> "Freezing drizzle"
        61, 63, 65 -> "Rain"
        66, 67 -> "Freezing rain"
        71, 73, 75 -> "Snow"
        77 -> "Snow grains"
        80, 81, 82 -> "Rain showers"
        85, 86 -> "Snow showers"
        95 -> "Thunderstorm"
        96, 99 -> "Thunderstorm with hail"
        else -> "Unknown"
    }
}