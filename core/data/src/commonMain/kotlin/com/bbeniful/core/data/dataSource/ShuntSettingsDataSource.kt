package com.bbeniful.core.data.dataSource

import ShuntSettings
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow

interface ShuntSettingsDataSource {

    val shuntSettings: Flow<ShuntSettings>

    suspend fun setManufacture(manufacture: String)

    suspend fun setModel(model: String)

    suspend fun setCurrentPressure(currentPressure: Double)

    suspend fun setLastModifiedDate(lastModifiedDate: String)

}

class ShuntSettingsDataSourceImpl(
    private val shuntSettingsDataStore: DataStore<ShuntSettings>
) : ShuntSettingsDataSource {

    override val shuntSettings: Flow<ShuntSettings>
        get() = shuntSettingsDataStore.data

    override suspend fun setManufacture(manufacture: String) {
        shuntSettingsDataStore.updateData { current ->
            current.copy(manufacture = manufacture)
        }
    }

    override suspend fun setModel(model: String) {
        shuntSettingsDataStore.updateData { current ->
            current.copy(model = model)
        }
    }

    override suspend fun setCurrentPressure(currentPressure: Double) {
        shuntSettingsDataStore.updateData { current ->
            current.copy(current_pressure = currentPressure)
        }
    }

    override suspend fun setLastModifiedDate(lastModifiedDate: String) {
        shuntSettingsDataStore.updateData { current ->
            current.copy(last_modified_date = lastModifiedDate)
        }
    }
}