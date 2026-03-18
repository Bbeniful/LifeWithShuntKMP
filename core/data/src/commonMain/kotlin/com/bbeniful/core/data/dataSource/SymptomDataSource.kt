package com.bbeniful.core.data.dataSource

import com.bbeniful.core.data.database.dao.SymptomDao
import com.bbeniful.core.data.database.entity.SymptomEntity
import com.bbeniful.core.domain.model.Symptom

interface SymptomDataSource {
    suspend fun add(symptom: SymptomEntity)

    suspend fun remove(symptom: SymptomEntity)

   suspend fun getAllSymptom(): List<SymptomEntity>
}

class SymptomDataSourceImpl(
    private val symptomDao: SymptomDao
) : SymptomDataSource {

    override suspend fun add(symptom: SymptomEntity) = symptomDao.add(symptom = symptom)

    override suspend fun remove(symptom: SymptomEntity) = symptomDao.remove(symptom = symptom)

    override suspend fun getAllSymptom() = symptomDao.getAllSymptom()

}