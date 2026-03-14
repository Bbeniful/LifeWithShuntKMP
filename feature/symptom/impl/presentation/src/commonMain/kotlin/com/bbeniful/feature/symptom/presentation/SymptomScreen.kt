package com.bbeniful.feature.symptom.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.bbeniful.core.presentation.token.Colors
import com.bbeniful.feature.symptom.presentation.components.Card
import com.bbeniful.feature.symptom.presentation.components.SliderComponent
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.Res
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_nausea
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_nausea

@Composable
fun SymptomScreen(backstack: SnapshotStateList<Any>) {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Colors.Background)
    ) {
        SliderComponent()
        Card(
            icon = Res.drawable.img_nausea,
            title = Res.string.symptom_nausea
        )
    }
}

