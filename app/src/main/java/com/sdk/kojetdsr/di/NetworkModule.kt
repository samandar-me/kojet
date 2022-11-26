package com.sdk.kojetdsr.di

import com.sdk.data.remote.api.WeatherService
import com.sdk.data.remote.repository.WeatherRepositoryImpl
import com.sdk.domain.repository.WeatherRepository
import com.sdk.domain.use_cases.AllUseCases
import com.sdk.domain.use_cases.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): WeatherService {
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherService): WeatherRepository {
        return WeatherRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideAllUseCases(repository: WeatherRepository): AllUseCases {
        return AllUseCases(
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repository)
        )
    }
}