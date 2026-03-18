package com.bbeniful.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bbeniful.core.presentation.token.Colors

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    spacing: Dp = 10.dp,
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Colors.ItemBackground,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = spacing,
            alignment = Alignment.CenterVertically
        )
    ) {
        icon()
        title()
        subtitle()
    }
}