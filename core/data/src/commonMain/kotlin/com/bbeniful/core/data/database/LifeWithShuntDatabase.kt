package com.bbeniful.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.bbeniful.core.data.database.dao.SymptomDao
import com.bbeniful.core.data.database.entity.SymptomEntity

@Database(
    entities = [SymptomEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class LifeWithShuntDatabase : RoomDatabase() {

    abstract fun symptomDao(): SymptomDao
}

@Suppress("KotlinNoActualForExpect", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<LifeWithShuntDatabase> {
    override fun initialize(): LifeWithShuntDatabase
}
