package com.bbeniful.core.data.provider

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import com.bbeniful.core.domain.model.LocationData
import com.bbeniful.core.domain.provider.LocationProvider
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class AndroidLocationProvider(private val context: Context) : LocationProvider {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(): Flow<LocationData?> = callbackFlow {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 30.seconds.inWholeMilliseconds
        ).build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let {
                    Log.e("Location", "Location: ${it.latitude}, ${it.longitude}")
                    trySend(LocationData(it.latitude, it.longitude, it.accuracy, it.altitude))
                }
            }
        }

        val last = fusedLocationClient.lastLocation

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            callback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationClient.removeLocationUpdates(callback)
        }
    }
}