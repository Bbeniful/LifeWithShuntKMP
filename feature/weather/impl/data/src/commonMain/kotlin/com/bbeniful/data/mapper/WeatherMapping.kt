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
    val currentWeather = current.toDomain(
        hourly = hourly
    )

    val dailyForecast = daily.toDomainForecast(
        hourly = hourly
    )

    return Weather(
        current = currentWeather,
        tomorrow = dailyForecast.getOrNull(1)
            ?: dailyForecast.firstOrNull()
            ?: throw IllegalStateException("Daily forecast is missing."),
        weeklyForecast = dailyForecast
    )
}

fun CurrentDto.toDomain(
    hourly: HourlyDto
): CurrentWeather {
    val weatherFront = hourly.calculateWeatherFront(
        currentTime = time
    )

    return CurrentWeather(
        temperatureCelsius = temperature_2m,
        apparentTemperatureCelsius = apparent_temperature,
        weatherCode = weather_code,
        humidityPercent = null,
        windSpeedKmH = null,
        pressureHpa = pressure_msl,
        weatherFront = weatherFront,
        description = weather_code.toWeatherDescription(),
        observationTimeIso = time
    )
}

fun DailyDto.toDomainForecast(
    hourly: HourlyDto
): List<DailyWeather> {
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

fun HourlyDto.calculateWeatherFront(
    currentTime: String
): WeatherFront {
    val currentIndex = time.indexOf(currentTime)

    if (currentIndex == -1) return WeatherFront.Low

    val currentPressure = pressure_msl.getOrNull(currentIndex) ?: return WeatherFront.Low
    val pressure3hAgo = pressure_msl.getOrNull(currentIndex - 3)
    val pressure6hAgo = pressure_msl.getOrNull(currentIndex - 6)

    val delta3h = pressure3hAgo?.let { currentPressure - it }
    val delta6h = pressure6hAgo?.let { currentPressure - it }

    val maxChange = listOfNotNull(
        delta3h?.let(::abs),
        delta6h?.let(::abs)
    ).maxOrNull() ?: 0.0

    return maxChange.toWeatherFront()
}

fun HourlyDto.calculateWeatherFrontForDate(
    dateIso: String
): WeatherFront {
    val pressuresForDate = time.indices
        .filter { index -> time[index].startsWith(dateIso) }
        .mapNotNull { index -> pressure_msl.getOrNull(index) }

    if (pressuresForDate.size < 2) return WeatherFront.Low

    val minPressure = pressuresForDate.minOrNull() ?: return WeatherFront.Low
    val maxPressure = pressuresForDate.maxOrNull() ?: return WeatherFront.Low
    val change = abs(maxPressure - minPressure)

    return change.toWeatherFront()
}

fun HourlyDto.averagePressureForDate(
    dateIso: String
): Double? {
    val pressuresForDate = time.indices
        .filter { index -> time[index].startsWith(dateIso) }
        .mapNotNull { index -> pressure_msl.getOrNull(index) }

    if (pressuresForDate.isEmpty()) return null

    return pressuresForDate.average()
}

fun Double.toWeatherFront(): WeatherFront {
    return when {
        this >= 6.0 -> WeatherFront.High
        this >= 3.0 -> WeatherFront.Medium
        else -> WeatherFront.Low
    }
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