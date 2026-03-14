package com.bbeniful.feature.symptom.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bbeniful.core.presentation.token.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Card(
    icon: DrawableResource,
    title: StringResource,
    isSelected: Boolean = false
) {

    Column(
        modifier = Modifier.width(150.dp)
            .height(80.dp)
            .background(color = Colors.ItemBackground, shape = RoundedCornerShape(20.dp))
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(

            ) {

            }
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = ""
        )
        Text(text = stringResource(title))
    }
}