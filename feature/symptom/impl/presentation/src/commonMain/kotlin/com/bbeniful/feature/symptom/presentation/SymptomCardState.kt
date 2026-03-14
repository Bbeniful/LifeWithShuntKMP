package com.bbeniful.feature.symptom.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.bbeniful.core.domain.model.Symptom
import com.bbeniful.core.domain.model.SymptomName
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.Res
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_dizziness
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_headache
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_lethargy
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_nausea
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_tingling
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.img_vision
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.none
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_dizziness
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_headache
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_lethargy
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_nausea
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_tingling
import lifewithshunt.feature.symptom.impl.presentation.generated.resources.symptom_vision
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Immutable
class SymptomCardState {

    private var symptomList = listOf(
        SymptomName.Nausea.value,
        SymptomName.Dizziness.value,
        SymptomName.Lethargy.value,
        SymptomName.Vision.value,
        SymptomName.Headache.value,
        SymptomName.Tingling.value
    )

    fun getSymptomUIs() = symptomList.map { getStringResForSymptom(it) }

    private fun getStringResForSymptom(name: String): SymptomUI = when (name) {
        SymptomName.Nausea.value -> {
            SymptomUI(
                icon = Res.drawable.img_nausea,
                title = Res.string.symptom_nausea
            )
        }

        SymptomName.Dizziness.value -> {
            SymptomUI(
                icon = Res.drawable.img_dizziness,
                title = Res.string.symptom_dizziness
            )
        }

        SymptomName.Lethargy.value -> {
            SymptomUI(
                icon = Res.drawable.img_lethargy,
                title = Res.string.symptom_lethargy
            )
        }

        SymptomName.Vision.value -> {
            SymptomUI(
                icon = Res.drawable.img_vision,
                title = Res.string.symptom_vision
            )
        }

        SymptomName.Headache.value -> {
            SymptomUI(
                icon = Res.drawable.img_headache,
                title = Res.string.symptom_headache
            )
        }

        SymptomName.Tingling.value -> {
            SymptomUI(
                icon = Res.drawable.img_tingling,
                title = Res.string.symptom_tingling
            )
        }

        else -> {
            SymptomUI(
                icon = Res.drawable.img_nausea,
                title = Res.string.none
            )
        }
    }

    var selectedSymptoms = mutableStateListOf<SymptomUI>()

    fun addOrRemoveSymptom(symptomUI: SymptomUI) {
        if (symptomUI in selectedSymptoms) {
            selectedSymptoms.remove(symptomUI)
            return
        }

        selectedSymptoms.add(symptomUI)
    }
}

data class SymptomUI(
    val icon: DrawableResource,
    val title: StringResource
)

@Composable
fun rememberSymptomCardState() = remember { SymptomCardState() }