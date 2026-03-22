package com.bbeniful.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OpenMeteoResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentDto,
    val hourly: HourlyDto,
    val daily: DailyDto
)

@Serializable
data class CurrentDto(
    val time: String,
    val temperature_2m: Double,
    val apparent_temperature: Double,
    val pressure_msl: Double,
    val weather_code: Int,
    val wind_speed_10m: Double,
    val cloud_cover: Double
)

@Serializable
data class HourlyDto(
    val time: List<String>,
    val pressure_msl: List<Double>,
    val temperature_2m: List<Double>,
    val wind_speed_10m: List<Double>,
    val cloud_cover: List<Double>
)

@Serializable
data class DailyDto(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)