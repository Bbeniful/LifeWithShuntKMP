package com.bbeniful.feature.home.presentation

import com.bbeniful.core.domain.model.WeatherFront
import lifewithshunt.feature.home.impl.presentation.generated.resources.Res
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_clear_sky
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_drizzle
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_fog
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_partly_cloud
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_rain
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_rain_shower
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_snow
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_snow_grains
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_snow_shower
import lifewithshunt.feature.home.impl.presentation.generated.resources.icon_thunderstorm
import org.jetbrains.compose.resources.DrawableResource

data class HomeUIState(
    val userName: String = "",
    val currentWeather: DisplayWeather = DisplayWeather(),
    val error: String = ""
)

data class DisplayWeather(
    val isLoading: Boolean = false,
    val currentCelsius: String = "",
    val frontType: WeatherFront = WeatherFront.None,
    val weatherCode: Int? = null,
    val weatherTitle: String = "",
    val weatherDescription: String = "",
    val errorLoadingWeather: String? = null
)

val DisplayWeather.formatCelsius: String
    get() = "$currentCelsius°C"


val DisplayWeather.weatherIcon: DrawableResource
    get() = when (weatherCode) {
        0 -> Res.drawable.icon_clear_sky
        1, 2, 3 -> Res.drawable.icon_partly_cloud
        45, 48 -> Res.drawable.icon_fog
        51, 53, 55 -> Res.drawable.icon_drizzle
        56, 57 -> Res.drawable.icon_fog // need to add it
        61, 63, 65 -> Res.drawable.icon_rain
        66, 67 -> Res.drawable.icon_rain // need to add it
        71, 73, 75 -> Res.drawable.icon_snow
        77 -> Res.drawable.icon_snow_grains
        80, 81, 82 -> Res.drawable.icon_rain_shower
        85, 86 -> Res.drawable.icon_snow_shower
        95, 96,99 -> Res.drawable.icon_thunderstorm
        else -> Res.drawable.icon_clear_sky
    }

