package com.bbeniful.core.data.repository

import com.bbeniful.core.data.dataSource.SymptomDataSource
import com.bbeniful.core.data.mapper.toData
import com.bbeniful.core.data.mapper.toDomain
import com.bbeniful.core.domain.model.Symptom
import com.bbeniful.core.domain.repository.SymptomRepository

class SymptomRepositoryImpl(
    private val symptomDataSource: SymptomDataSource
) : SymptomRepository {
    override suspend fun add(symptom: Symptom) = symptomDataSource.add(symptom = symptom.toData)

    override suspend fun getLastSymptom() = symptomDataSource.getAllSymptom().last().toDomain

    override suspend fun remove(symptom: Symptom) =
        symptomDataSource.remove(symptom = symptom.toData)

    override suspend fun getAllSymptom() = symptomDataSource.getAllSymptom().map { it.toDomain }
}