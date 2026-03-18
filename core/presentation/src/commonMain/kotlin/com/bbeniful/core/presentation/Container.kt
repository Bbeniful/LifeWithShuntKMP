@file:OptIn(KoinExperimentalAPI::class)

package com.bbeniful.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.bbeniful.core.presentation.menu.BottomItem
import com.bbeniful.core.presentation.menu.BottomMenu
import com.bbeniful.core.presentation.token.Colors
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
            Scaffold(
                modifier = Modifier.fillMaxSize()
                    .background(Colors.Background),
                containerColor = Colors.Background
            ) { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)) {

                    val navigator = koinInject<Navigator>()
                    val entries = koinEntryProvider<Any>()

                    NavDisplay(
                        backStack = navigator.backstack,
                        onBack = navigator::goBack,
                        entryProvider = entries
                    )

                    BottomMenu(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        items = listOf(
                            BottomItem.Home,
                            BottomItem.Symptom
                        ),
                        backstack = navigator.backstack
                    )
                }
            }
        }
    )
}