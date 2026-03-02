@file:OptIn(KoinExperimentalAPI::class)

package com.bbeniful.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.Module
import org.koin.dsl.koinConfiguration

@Composable
fun KoinAppContainer(initialModules: () -> Module) {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(initialModules()) }),
        content = {

            val navigator = koinInject<Navigator>()
            val entries = koinEntryProvider<Any>()

            NavDisplay(
                backStack = navigator.backstack,
                onBack = navigator::goBack,
                entryProvider = entries
            )
        })
}