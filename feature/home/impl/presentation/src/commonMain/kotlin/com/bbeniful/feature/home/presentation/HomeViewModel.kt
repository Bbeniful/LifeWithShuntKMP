package com.bbeniful.feature.home.presentation

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbeniful.core.domain.model.Weather
import com.bbeniful.core.domain.usecase.GetLocationUseCase
import com.bbeniful.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.floor

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun getWeather(isRefreshing: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase().collectLatest { location ->
                if (location == null) {
                    updateError(error = "Cannot load Location")
                    return@collectLatest
                }
                updateWeatherLoading(isLoading = true)

                getWeatherUseCase(
                    longitude = location.longitude,
                    latitude = location.latitude
                ).run {
                    updateWeatherLoading(isLoading = false)
                    updateWeather(this)
                }
            }
        }
    }

    private fun updateError(error: String) {
        _uiState.update {
            it.copy(
                error = error
            )
        }
    }

    private fun updateWeatherLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(
                currentWeather = DisplayWeather(
                    isLoading = isLoading
                )
            )
        }
    }

    private fun refreshing(): Boolean {
        return false
    }

    private fun updateWeather(weather: Weather) {
        _uiState.update {
            it.copy(
                currentWeather = DisplayWeather(
                    currentCelsius = ceil(weather.current.temperatureCelsius).toInt().toString(),
                    frontType = weather.current.weatherFront,
                    weatherCode = weather.current.weatherCode,
                    weatherTitle = weather.current.description ?: "Unknown"
                )
            )
        }
    }
}