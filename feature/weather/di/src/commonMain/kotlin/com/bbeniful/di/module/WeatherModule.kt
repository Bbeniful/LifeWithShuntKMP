package com.bbeniful.di.module

import com.bbeniful.data.dataSource.WeatherDataSource
import com.bbeniful.data.dataSource.WeatherDataSourceImpl
import com.bbeniful.data.repository.WeatherRepositoryImpl
import com.bbeniful.domain.repository.WeatherRepository
import com.bbeniful.domain.usecase.GetWeatherUseCase
import com.bbeniful.domain.usecase.GetWeatherUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    singleOf(::WeatherDataSourceImpl) bind WeatherDataSource::class
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class

    factoryOf(::GetWeatherUseCaseImpl) bind GetWeatherUseCase::class
}