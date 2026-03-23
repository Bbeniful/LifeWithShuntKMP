@file:OptIn(ExperimentalForeignApi::class)

package com.bbeniful.core.di

import ShuntSettings
import UserSettings
import androidx.datastore.core.DataStore
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bbeniful.core.data.database.LifeWithShuntDatabase
import com.bbeniful.core.data.datastore.createShuntSettingsDataStore
import com.bbeniful.core.data.datastore.createUserSettingsDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

fun createDataBase(context: Any?): RoomDatabase.Builder<LifeWithShuntDatabase> {
    val dbFile = NSHomeDirectory() + "/LifeWithShuntDatabase.db"
    return Room.databaseBuilder<LifeWithShuntDatabase>(
        name = dbFile
    )
}

actual val databaseModule: Module
    get() = module {
        single<LifeWithShuntDatabase> {
            createDataBase(null).build()

        }
    }
actual val dataStoreUserSettingsModule: Module
    get() = module {
        single<DataStore<UserSettings>> {
            createUserSettingsDataStore(null)
        }
    }
actual val dataStoreShuntSettingsModule: Module
    get() = module {
        single<DataStore<ShuntSettings>> {
            createShuntSettingsDataStore(null)
        }
    }