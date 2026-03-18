package com.bbeniful.feature.home.presentation

import com.bbeniful.core.domain.model.WeatherFront

data class HomeUIState(
    val userName: String = "",
    val currentWeather: DisplayWeather? = null,
    val error: String = ""
)

data class DisplayWeather(
    val currentCelsius: String,
    val frontType: WeatherFront
)

