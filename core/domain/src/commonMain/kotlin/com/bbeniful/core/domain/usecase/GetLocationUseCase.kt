package com.bbeniful.core.domain.usecase

import com.bbeniful.core.domain.provider.LocationProvider

class GetLocationUseCase(
    private val locationProvider: LocationProvider
) {

    operator fun invoke() = locationProvider.getLocationUpdates()
}