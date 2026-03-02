package com.bbeniful.core.di


import com.bbeniful.core.data.provider.AndroidLocationProvider
import com.bbeniful.core.domain.provider.LocationProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val locationModule: Module
    get() = module {
        singleOf(::AndroidLocationProvider) bind LocationProvider::class
    }