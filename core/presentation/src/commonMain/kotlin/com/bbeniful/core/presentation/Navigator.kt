package com.bbeniful.core.presentation

import androidx.compose.runtime.mutableStateListOf

class Navigator(startDestination: Any) {
    var backstack = mutableStateListOf(startDestination)

    fun goTo(route: Any) = backstack.add(route)

    fun goBack() {
        backstack.removeLastOrNull()
    }
}