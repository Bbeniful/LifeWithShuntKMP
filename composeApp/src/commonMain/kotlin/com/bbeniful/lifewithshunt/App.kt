package com.bbeniful.lifewithshunt

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bbeniful.core.di.appModule
import com.bbeniful.core.di.dataModule
import com.bbeniful.core.di.navigatorModule
import com.bbeniful.core.presentation.KoinAppContainer
import com.bbeniful.feature.home.di.homeNavModule
import com.bbeniful.feature.symptom.di.symptomNavigationModule
import org.jetbrains.compose.resources.painterResource

import lifewithshunt.composeapp.generated.resources.Res
import lifewithshunt.composeapp.generated.resources.compose_multiplatform
import org.koin.dsl.module


private val modules = module {
    includes(
        appModule,
        navigatorModule,
        homeNavModule,
        dataModule,
        symptomNavigationModule
    )
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinAppContainer {
            modules
        }
    }
}