package com.bbeniful.domain.usecase

import com.bbeniful.core.domain.model.Weather

interface GetWeatherUseCase {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Weather
}