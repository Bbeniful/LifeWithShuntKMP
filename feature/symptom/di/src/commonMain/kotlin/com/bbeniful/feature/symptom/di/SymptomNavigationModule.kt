@file:OptIn(KoinExperimentalAPI::class)

package com.bbeniful.feature.symptom.di

import com.bbeniful.core.presentation.Navigator
import com.bbeniful.feature.api.nav.SymptomNavRoute
import com.bbeniful.feature.symptom.presentation.SymptomScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

val symptomNavigationModule = module {
    navigation<SymptomNavRoute> {
        SymptomScreen(get<Navigator>().backstack)
    }
}