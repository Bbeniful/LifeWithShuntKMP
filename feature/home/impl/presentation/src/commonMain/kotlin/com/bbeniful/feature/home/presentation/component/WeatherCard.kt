package com.bbeniful.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.core.presentation.token.Colors
import com.bbeniful.feature.home.presentation.DisplayWeather
import com.bbeniful.feature.home.presentation.weatherIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherCard(weather: DisplayWeather?, onRefresh: () -> Unit) {
    check(weather != null) {
        "Weather object is null"
    }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(color = Colors.ItemBackground, shape = RoundedCornerShape(20.dp))
            .border(width = 3.dp, color = Colors.HighElement, shape = RoundedCornerShape(20.dp))
            .padding(40.dp)
    ) {

        when {
            weather.errorLoadingWeather != null -> {
                ErrorInLoadingWeather(error = weather.errorLoadingWeather)
            }

            weather.isLoading -> {
                LoadingWeather()
            }

            else -> {
                WeatherData(
                    weather = weather,
                    onRefresh = onRefresh
                )
            }
        }
    }
}

@Composable
internal fun LoadingWeather() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun WeatherData(weather: DisplayWeather, onRefresh: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .size(110.dp),
            painter = painterResource(weather.weatherIcon),
            contentDescription = weather.weatherTitle,
            tint = Color.White
        )

        Column {
            Text(
                text = weather.weatherTitle,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weather.currentCelsius + "°C",
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "${weather?.frontType?.name}",
                color = Color.White,
                fontSize = 18.sp
            )

            // implement it later
            /* Icon(
                 modifier = Modifier.clickable(
                     interactionSource = remember { MutableInteractionSource() },
                     indication = null,
                     onClick = onRefresh
                 ),
                 painter = painterResource(Res.drawable.icon_snow),
                 contentDescription = "Refresh",
                 tint = Color.White
             )*/
        }
    }


}

@Composable
internal fun ErrorInLoadingWeather(error: String?) {
    Text(text = error ?: "Unknown error")
}

