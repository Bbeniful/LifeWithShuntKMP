package com.bbeniful.core.data.mapper.serializer

import ShuntSettings
import UserSettings
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.okio.OkioSerializer
import okio.BufferedSink
import okio.BufferedSource
import okio.IOException

object UserSettingsSerializer : OkioSerializer<UserSettings> {

    override val defaultValue: UserSettings = UserSettings()

    override suspend fun readFrom(source: BufferedSource): UserSettings =
        try {
            UserSettings.ADAPTER.decode(source)
        } catch (e: IOException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: UserSettings, sink: BufferedSink) {
        UserSettings.ADAPTER.encode(sink, t)
    }
}


object ShuntSettingsSerializer: OkioSerializer<ShuntSettings> {

    override val defaultValue: ShuntSettings = ShuntSettings()

    override suspend fun readFrom(source: BufferedSource): ShuntSettings =
        try {
            ShuntSettings.ADAPTER.decode(source)
        } catch (e: IOException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: ShuntSettings, sink: BufferedSink) {
        ShuntSettings.ADAPTER.encode(sink, t)
    }
}
