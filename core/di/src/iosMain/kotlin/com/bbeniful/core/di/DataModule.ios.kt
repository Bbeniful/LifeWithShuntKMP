package com.bbeniful.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.bbeniful.core.data.database.LifeWithShuntDatabase
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