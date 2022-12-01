package com.sdk.kojetdsr.di

import android.content.Context
import android.location.Geocoder
import com.sdk.data.local.database.LocationNameDao
import com.sdk.data.remote.api.WeatherService
import com.sdk.data.repository.WeatherRepositoryImpl
import com.sdk.domain.repository.WeatherRepository
import com.sdk.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context)
    }

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
    fun provideWeatherRepository(service: WeatherService, dao: LocationNameDao): WeatherRepository {
        return WeatherRepositoryImpl(service, dao)
    }

    @Singleton
    @Provides
    fun provideAllUseCases(repository: WeatherRepository): AllUseCases {
        return AllUseCases(
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repository),
            saveLocationNameBaseUseCase = SaveLocationNameUseCase(repository),
            getLocationNamesUseCase = GetLocationNamesUseCase(repository),
            updateLocationNameUseCase = UpdateLocationNameUseCase(repository),
            updateFavLocationName = UpdateFavLocationName(repository)
        )
    }
}