package com.bbeniful.feature.symptom.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bbeniful.core.presentation.token.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SymptomCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    isSelected: Boolean = false
) {

    Column(
        modifier = modifier.width(160.dp)
            .height(120.dp)
            .background(color = Colors.ItemBackground, shape = RoundedCornerShape(20.dp))
            .border(
                width = 4.dp,
                color = if (isSelected) Colors.HighElement else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            tint = Colors.HighElement,
            painter = painterResource(icon),
            contentDescription = stringResource(title)
        )
        Text(text = stringResource(title), color = Color.White, fontWeight = FontWeight.Bold)
    }
}