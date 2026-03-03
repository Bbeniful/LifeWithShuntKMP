package com.bbeniful.core.domain.repository

import com.bbeniful.core.domain.model.Symptom

interface SymptomRepository {

    suspend fun add(symptom: Symptom)

    fun getLastSymptom(): Symptom

    suspend fun remove(symptom: Symptom)

    fun getAllSymptom(): List<Symptom>
}