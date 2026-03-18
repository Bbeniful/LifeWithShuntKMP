package com.bbeniful.core.domain.repository

import com.bbeniful.core.domain.model.Symptom

interface SymptomRepository {

    suspend fun add(symptom: Symptom)

    suspend fun getLastSymptom(): Symptom

    suspend fun remove(symptom: Symptom)

    suspend fun getAllSymptom(): List<Symptom>
}