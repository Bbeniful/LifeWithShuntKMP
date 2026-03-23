package com.bbeniful.core.data.datastore

import ShuntSettings
import UserSettings
import android.content.Context
import androidx.datastore.core.DataStore
import com.bbeniful.core.data.mapper.serializer.ShuntSettingsSerializer
import com.bbeniful.core.data.mapper.serializer.UserSettingsSerializer
import okio.FileSystem
import okio.Path.Companion.toPath

actual fun createUserSettingsDataStore(context: Any?): DataStore<UserSettings> {

    if (context !is Context) {
        throw IllegalArgumentException("Context must be an instance of Context")
    }
   return createDataStore(
        fileSystem = FileSystem.SYSTEM,
       serializer = UserSettingsSerializer,
        producePath = {
            context.filesDir.resolve(USER_SETTINGS_DATA_STORE_FILE).absolutePath.toPath()
        }
    )
}

actual fun createShuntSettingsDataStore(context: Any?): DataStore<ShuntSettings> {
    if (context !is Context) {
        throw IllegalArgumentException("Context must be an instance of Context")
    }

    return createDataStore(
        fileSystem = FileSystem.SYSTEM,
        serializer = ShuntSettingsSerializer,
        producePath = {
            context.filesDir.resolve(USER_SETTINGS_DATA_STORE_FILE).absolutePath.toPath()
        }
    )
}