package com.bbeniful.feature.symptom.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.bbeniful.core.domain.model.SymptomName
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.Res
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_nausea
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Immutable
class SymptomCardState {

    var symptomList = listOf(
        SymptomName.Nausea.value,
        SymptomName.Dizziness.value,
        SymptomName.Lethargy.value,
        SymptomName.Vision.value,
        SymptomName.Headache.value,
        SymptomName.Tingling.value
    )

    var selectedSymptomps = mutableListOf<SymptomName>()


}

data class SymptomTile(
    val icon: DrawableResource,
    val title: StringResource
)

@Composable
fun rememberSymptomCardState() = remember { SymptomCardState() }