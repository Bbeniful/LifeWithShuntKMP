package com.bbeniful.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bbeniful.core.data.database.LifeWithShuntDatabase
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