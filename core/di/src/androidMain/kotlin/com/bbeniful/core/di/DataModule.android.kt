package com.bbeniful.core.di

import ShuntSettings
import UserSettings
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bbeniful.core.data.database.LifeWithShuntDatabase
import com.bbeniful.core.data.datastore.createShuntSettingsDataStore
import com.bbeniful.core.data.datastore.createUserSettingsDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

fun createDataBase(context: Any?): RoomDatabase.Builder<LifeWithShuntDatabase> {
    check(context is Context) {
        "Context must be from Android framework"
    }

    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("LifeWithShuntDatabase.db")
    return Room.databaseBuilder<LifeWithShuntDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

actual val databaseModule: Module
    get() = module {
        single<LifeWithShuntDatabase> {
            createDataBase(context = get<Context>()).build()
        }
    }
actual val dataStoreUserSettingsModule: Module
    get() = module {
        single<DataStore<UserSettings>> {
            createUserSettingsDataStore(context = get<Context>())
        }
    }
actual val dataStoreShuntSettingsModule: Module
    get() =  module {
    single<DataStore<ShuntSettings>> {
        createShuntSettingsDataStore(context = get<Context>())
    }
}