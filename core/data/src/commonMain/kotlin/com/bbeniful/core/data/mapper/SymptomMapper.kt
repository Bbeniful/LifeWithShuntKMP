package com.bbeniful.core.data.mapper

import com.bbeniful.core.data.database.entity.SymptomEntity
import com.bbeniful.core.domain.model.Symptom

val SymptomEntity.toDomain: Symptom
    get() = Symptom(
        id = id,
        nameOfSymptom = nameOfSymptom,
        date = date,
        note = note
    )

val Symptom.toData: SymptomEntity
    get() = SymptomEntity(
        id = id,
        nameOfSymptom = nameOfSymptom,
        date = date,
        note = note
    )