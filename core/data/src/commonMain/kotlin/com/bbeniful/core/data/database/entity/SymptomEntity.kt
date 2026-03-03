package com.bbeniful.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbeniful.core.domain.model.SymptomName

@Entity
data class SymptomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    val nameOfSymptom: String = SymptomName.None.value,
    val note: String = "",
    val date: String = ""
)