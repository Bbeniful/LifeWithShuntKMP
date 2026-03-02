package com.bbeniful.core.domain.provider

import com.bbeniful.core.domain.model.LocationData
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    fun getLocationUpdates(): Flow<LocationData?>
}