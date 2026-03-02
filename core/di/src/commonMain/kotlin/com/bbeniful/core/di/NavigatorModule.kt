package com.bbeniful.core.di

import com.bbeniful.core.presentation.Navigator
import com.bbeniful.nav.HomeRoute
import org.koin.dsl.module

val navigatorModule = module {
    single<Navigator> {
        Navigator(startDestination = HomeRoute)
    }
}