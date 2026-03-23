package com.bbeniful.core.di

import com.bbeniful.core.data.dataSource.ShuntSettingsDataSource
import com.bbeniful.core.data.dataSource.ShuntSettingsDataSourceImpl
import com.bbeniful.core.data.dataSource.SymptomDataSource
import com.bbeniful.core.data.dataSource.SymptomDataSourceImpl
import com.bbeniful.core.data.dataSource.UserSettingsDataSource
import com.bbeniful.core.data.dataSource.UserSettingsDataSourceImpl
import com.bbeniful.core.data.database.LifeWithShuntDatabase
import com.bbeniful.core.data.database.dao.SymptomDao
import com.bbeniful.core.data.repository.SymptomRepositoryImpl
import com.bbeniful.core.domain.repository.SymptomRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val databaseModule: Module

expect val dataStoreUserSettingsModule: Module

expect val dataStoreShuntSettingsModule: Module

val dataModule = module {
    includes(
        databaseModule,
        dataStoreUserSettingsModule,
        dataStoreShuntSettingsModule
    )

    single<SymptomDao> {
        get<LifeWithShuntDatabase>().symptomDao()
    }

    singleOf(::UserSettingsDataSourceImpl) bind UserSettingsDataSource::class
    singleOf(::ShuntSettingsDataSourceImpl) bind ShuntSettingsDataSource::class
    singleOf(::SymptomDataSourceImpl) bind SymptomDataSource::class
    singleOf(::SymptomRepositoryImpl) bind SymptomRepository::class
}