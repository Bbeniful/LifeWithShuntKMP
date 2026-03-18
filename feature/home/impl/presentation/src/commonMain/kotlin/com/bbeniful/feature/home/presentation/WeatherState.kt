package com.bbeniful.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.bbeniful.core.domain.model.Weather

@Immutable
class WeatherState(
    private val weather: Weather
) {

}

@Composable
fun rememberWeatherState(
    weather: Weather
) = remember { WeatherState(
    weather = weather
) }