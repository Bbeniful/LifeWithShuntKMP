@file:OptIn(KoinExperimentalAPI::class)

package com.bbeniful.feature.home.di

import com.bbeniful.feature.home.presentation.HomeScreen
import com.bbeniful.nav.HomeRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

val homeNavModule = module {
    navigation<HomeRoute> {
        HomeScreen()
    }
}