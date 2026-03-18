package com.bbeniful.domain.usecase

import com.bbeniful.core.domain.model.Weather
import com.bbeniful.domain.repository.WeatherRepository

class GetWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : GetWeatherUseCase {

    override suspend fun invoke(latitude: Double, longitude: Double): Weather =
        repository.getWeather(
            latitude,
            longitude
        )
}