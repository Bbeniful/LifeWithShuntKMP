@file:OptIn(ExperimentalForeignApi::class)

package com.bbeniful.core.data.datastore

import ShuntSettings
import UserSettings
import androidx.datastore.core.DataStore
import com.bbeniful.core.data.mapper.serializer.ShuntSettingsSerializer
import com.bbeniful.core.data.mapper.serializer.UserSettingsSerializer
import kotlinx.cinterop.ExperimentalForeignApi
import okio.FileSystem
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun createUserSettingsDataStore(context: Any?): DataStore<UserSettings> = createDataStore(
    fileSystem = FileSystem.SYSTEM,
    serializer = UserSettingsSerializer,
    producePath = {
        val docDir = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        "${requireNotNull(docDir).path}/$USER_SETTINGS_DATA_STORE_FILE".toPath()
    }
)

actual fun createShuntSettingsDataStore(context: Any?): DataStore<ShuntSettings>  = createDataStore(
    fileSystem = FileSystem.SYSTEM,
    serializer = ShuntSettingsSerializer,
    producePath = {
        val docDir = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        "${requireNotNull(docDir).path}/$USER_SETTINGS_DATA_STORE_FILE".toPath()
    }
)