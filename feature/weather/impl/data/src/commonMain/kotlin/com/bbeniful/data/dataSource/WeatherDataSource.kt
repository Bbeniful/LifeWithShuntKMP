package com.bbeniful.data.dataSource

import com.bbeniful.data.model.OpenMeteoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface WeatherDataSource {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        timezone: String = "auto"
    ): OpenMeteoResponse
}


class WeatherDataSourceImpl(
    private val client: HttpClient
): WeatherDataSource {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        timezone: String
    ): OpenMeteoResponse {

        return client.get("https://api.open-meteo.com/v1/forecast") {

            parameter("latitude", latitude)
            parameter("longitude", longitude)

            parameter(
                "current",
                "temperature_2m,apparent_temperature,pressure_msl,weather_code"
            )

            parameter(
                "hourly",
                "pressure_msl"
            )

            parameter(
                "daily",
                "weather_code,temperature_2m_max,temperature_2m_min"
            )

            parameter("forecast_days", 7)

            parameter("timezone", timezone)

        }.body()
    }
}