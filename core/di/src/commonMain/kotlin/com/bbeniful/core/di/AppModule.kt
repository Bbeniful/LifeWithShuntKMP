package com.bbeniful.core.di

import com.bbeniful.core.domain.usecase.GetLocationUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

expect val locationModule: Module

val appModule = module {
    includes(locationModule)
    factoryOf(::GetLocationUseCase)
}