package com.bbeniful.core.data.dataSource

import UserSettings
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
interface UserSettingsDataSource {

    val userSettings: Flow<UserSettings>

    suspend fun setLastName(lastName: String)

    suspend fun setFirstName(firstName: String)

    suspend fun setUserName(userName: String)

    suspend fun setLocation(location: String)
}

class UserSettingsDataSourceImpl(
    private val userSettingsDataStore: DataStore<UserSettings>
) : UserSettingsDataSource {
    override val userSettings: Flow<UserSettings>
        get() = userSettingsDataStore.data


    override suspend fun setLastName(lastName: String) {
        userSettingsDataStore.updateData { current ->
            current.copy(last_name = lastName)
        }
    }

    override suspend fun setFirstName(firstName: String) {
        userSettingsDataStore.updateData { current ->
            current.copy(first_name = firstName)
        }
    }

    override suspend fun setUserName(userName: String) {
        userSettingsDataStore.updateData { current ->
            current.copy(user_name = userName)
        }
    }

    override suspend fun setLocation(location: String) {
        userSettingsDataStore.updateData { current ->
            current.copy(location = location)
        }
    }
}


