package com.bbeniful.core.domain.model

data class Weather(
    val current: CurrentWeather,
    val tomorrow: DailyWeather,
    val weeklyForecast: List<DailyWeather>
)

data class CurrentWeather(
    val temperatureCelsius: Double,
    val apparentTemperatureCelsius: Double?,
    val weatherCode: Int?,
    val humidityPercent: Int?,
    val windSpeedKmH: Double?,
    val pressureHpa: Double,
    val weatherFront: WeatherFront,
    val description: String?,
    val observationTimeIso: String
)

data class DailyWeather(
    val dateIso: String,
    val minTemperatureCelsius: Double,
    val maxTemperatureCelsius: Double,
    val weatherCode: Int?,
    val pressureHpa: Double?,
    val weatherFront: WeatherFront,
    val description: String?
)