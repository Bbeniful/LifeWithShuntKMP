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

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase().collectLatest { location ->
                if (location == null) {
                    updateError(error = "Cannot load Location")
                    return@collectLatest
                }

                getWeatherUseCase(
                    longitude = location.longitude,
                    latitude = location.latitude
                ).run {
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

    private fun updateWeather(weather: Weather) {
        _uiState.update {
            it.copy(
                currentWeather = DisplayWeather(
                    currentCelsius = weather.current.temperatureCelsius.toString(),
                    frontType = weather.current.weatherFront
                )
            )
        }
    }

}