package com.bbeniful.core.domain.model

data class Symptom(
    val id: Int = -1,
    val nameOfSymptom: String = SymptomName.None.value,
    val note: String = "",
    val date: String = ""
)

enum class SymptomName(val value: String) {
    Nausea(""),
    Dizziness(""),
    Lethargy(""),
    Vision(""),
    Headache(""),
    Tingling(""),
    None("")
}