package com.bbeniful.domain.repository

import com.bbeniful.core.domain.model.Weather

interface WeatherRepository {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather
}