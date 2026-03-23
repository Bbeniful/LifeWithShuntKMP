package com.bbeniful.core.data.datastore

import ShuntSettings
import UserSettings
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import com.bbeniful.core.data.mapper.serializer.UserSettingsSerializer
import okio.FileSystem
import okio.Path

const val USER_SETTINGS_DATA_STORE_FILE = "user_settings.pb"
const val SHUNT_SETTINGS_DATA_STORE_FILE = "shunt_settings.pb"




expect fun createUserSettingsDataStore(context: Any?): DataStore<UserSettings>

expect fun createShuntSettingsDataStore(context: Any?): DataStore<ShuntSettings>

fun <S> createDataStore(
    fileSystem: FileSystem,
    producePath: () -> Path,
    serializer: OkioSerializer<S>
): DataStore<S> = DataStoreFactory.create(
    storage = OkioStorage(
        fileSystem = fileSystem,
        serializer = serializer,
        producePath = producePath
    )
)
