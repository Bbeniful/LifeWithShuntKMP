package com.bbeniful.lifewithshunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            LocationPermissionWrapper {
                App()
            }
        }
    }

    @Preview
    @Composable
    fun AppAndroidPreview() {
        App()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun LocationPermissionWrapper(
        content: @Composable () -> Unit
    ) {
        // 1. Define the permission state
        val locationPermissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        // 2. Handle the different states
        if (locationPermissionState.allPermissionsGranted) {
            // Permission is granted, show the actual content (Map/Location data)
            content()
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val textToShow = if (locationPermissionState.shouldShowRationale) {
                    "The location is important for this app. Please grant the permission."
                } else {
                    "Location permission required for this feature."
                }

                Text(textToShow)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { locationPermissionState.launchMultiplePermissionRequest() }) {
                    Text("Request Permission")
                }
            }
        }
    }
}