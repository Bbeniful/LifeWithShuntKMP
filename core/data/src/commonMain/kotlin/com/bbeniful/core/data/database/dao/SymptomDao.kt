package com.bbeniful.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.bbeniful.core.data.database.entity.SymptomEntity
@Dao
interface SymptomDao {

    @Upsert
    suspend fun add(symptom: SymptomEntity)

    @Delete
    suspend fun remove(symptom: SymptomEntity)

    @Query("Select * From symptomentity")
    fun getAllSymptom(): List<SymptomEntity>
}