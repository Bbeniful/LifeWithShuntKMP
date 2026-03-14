package com.bbeniful.feature.symptom.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.bbeniful.core.presentation.token.Colors
import com.bbeniful.core.presentation.token.Colors.ItemBackground
import com.bbeniful.feature.symptom.presentation.components.SymptomCard
import com.bbeniful.feature.symptom.presentation.components.SliderComponent
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.Res
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_note_label
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_note_title
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SymptomScreen(backstack: SnapshotStateList<Any>) {

    val symptomState = rememberSymptomCardState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Colors.Background)
            .padding(horizontal = 20.dp)
    ) {
        SliderComponent()
        Symptoms(
            symptomList = symptomState.getSymptomUIs(),
            selectedSymptoms = symptomState.selectedSymptoms
        ) {
            symptomState.addOrRemoveSymptom(it)
        }
        AddNote()
    }
}

@Composable
internal fun Symptoms(
    symptomList: List<SymptomUI>,
    selectedSymptoms: List<SymptomUI>,
    onSymptomClick: (SymptomUI) -> Unit
) {

    Column {
        Text(
            text = stringResource(Res.string.symptom_title),
            color = Color.White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(symptomList) { symptom ->
                SymptomCard(
                    modifier = Modifier.clickable {
                        onSymptomClick(symptom)
                    },
                    icon = symptom.icon,
                    title = symptom.title,
                    isSelected = symptom in selectedSymptoms
                )
            }
        }
    }
}

@Composable
internal fun AddNote() {

    val noteTextState = rememberTextFieldState(initialText = "")

    Column {
        Text(
            text = stringResource(Res.string.symptom_note_title),
            color = Color.White.copy(alpha = 0.8f)
        )
        TextField(
            modifier = Modifier.fillMaxWidth()
                .heightIn(min = 120.dp),
            state = noteTextState,
            label = {
                Text(
                    text = stringResource(Res.string.symptom_note_label),
                    color = Color.White.copy(alpha = 0.4f)
                )
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedContainerColor = ItemBackground,
                unfocusedContainerColor = ItemBackground.copy(alpha = 0.5f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White.copy(alpha = 0.8f)
            )
        )
    }
}

