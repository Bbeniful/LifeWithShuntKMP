package com.bbeniful.feature.symptom.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

@Composable
fun SliderComponent() {

    var state by remember { mutableStateOf(0f) }
    LaunchedEffect(state) {
        println("State = $state")
    }

    PainIntensitySlider() {
        state = it
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PainIntensitySlider(
    initialValue: Float = 5f,
    onValueChange: (Float) -> Unit
) {
    var sliderValue by remember { mutableStateOf(initialValue) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How is your pain?",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            text = "Slide to indicate intensity",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "NO PAIN",
                color = Color(0xFF4CAF50),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "SEVERE",
                color = Color(0xFFE57373),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(contentAlignment = Alignment.Center) {
            androidx.compose.material3.Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    onValueChange(it.getCalculatedNum)
                },
                valueRange = 1f..10f,
                steps = 17,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(),
                thumb = {
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        tonalElevation = 4.dp,
                        shadowElevation = 4.dp,
                        modifier = Modifier.size(44.dp),
                        border = null
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = (sliderValue.getCalculatedNum).toString(),
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )
                        }
                    }
                },
                track = { sliderState ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFA5D6A7), // Green
                                        Color(0xFFFFF59D), // Yellow
                                        Color(0xFFFFCC80), // Orange
                                        Color(0xFFEF9A9A)  // Red
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            (1..10).forEach { i ->
                Text(
                    text = i.toString(),
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

val Float.getCalculatedNum: Float
    get() = ceil(this * 2) / 2