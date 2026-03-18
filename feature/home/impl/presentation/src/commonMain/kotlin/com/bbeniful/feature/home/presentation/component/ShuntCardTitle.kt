package com.bbeniful.feature.home.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShuntCardTitle() {
    Row {
        Column {
            Text(text = "ShuntCompanion")
            Text(text = "Safe and monitored")
        }

    }
}