package com.bbeniful.data.repository

import com.bbeniful.data.dataSource.WeatherDataSource
import com.bbeniful.data.mapper.toDomain
import com.bbeniful.core.domain.model.Weather
import com.bbeniful.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remote: WeatherDataSource,
    ): WeatherRepository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather {

        val response = remote.getWeather(
            latitude = latitude,
            longitude = longitude
        )

        return response.toDomain()
    }
}