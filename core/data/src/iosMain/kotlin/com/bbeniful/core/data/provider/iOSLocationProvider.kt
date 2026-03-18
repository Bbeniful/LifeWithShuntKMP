@file:OptIn(ExperimentalForeignApi::class)

package com.bbeniful.core.data.provider

import com.bbeniful.core.domain.model.LocationData
import com.bbeniful.core.domain.provider.LocationProvider
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.Foundation.NSError
import platform.darwin.NSObject


class IosLocationProvider : LocationProvider {
    private val locationManager = CLLocationManager()


    override fun getLocationUpdates(): Flow<LocationData?> = callbackFlow {
        val status = locationManager.authorizationStatus
        if (status == kCLAuthorizationStatusDenied || status == kCLAuthorizationStatusRestricted) {
            trySend(null)
        }

        val lastLocation = locationManager.location

        lastLocation?.let {
            trySend(
                LocationData(
                    latitude = it.coordinate.useContents { latitude },
                    longitude = it.coordinate.useContents { longitude },
                    accuracy = it.horizontalAccuracy.toFloat(),
                    altitude = it.altitude
                )
            )

        }

        awaitClose {
            locationManager.stopUpdatingLocation()
            locationManager.delegate = null
        }
    }
}
/*
this will ask continuously
class IosLocationProvider : LocationProvider {
    private val locationManager = CLLocationManager()

    override fun getLocationUpdates(): Flow<LocationData?> = callbackFlow {
        val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
            override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
                val location = didUpdateLocations.lastOrNull() as? CLLocation
                location?.let {
                    trySend(
                        LocationData(
                            latitude = it.coordinate.useContents { latitude },
                            longitude = it.coordinate.useContents { longitude },
                            accuracy = it.horizontalAccuracy.toFloat(),
                            altitude = it.altitude
                        )
                    )
                }
            }
        }

        locationManager.delegate = delegate
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()

        awaitClose {
            locationManager.stopUpdatingLocation()
            locationManager.delegate = null
        }
    }
}*/
