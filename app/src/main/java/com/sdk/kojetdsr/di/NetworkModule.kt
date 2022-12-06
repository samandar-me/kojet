package com.sdk.kojetdsr.di

import android.content.Context
import android.location.Geocoder
import com.sdk.data.local.database.FavoriteDao
import com.sdk.data.local.database.LocationNameDao
import com.sdk.data.remote.api.WeatherService
import com.sdk.data.repository.WeatherLocalRepositoryImpl
import com.sdk.data.repository.WeatherRemoteRepositoryImpl
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.repository.WeatherRemoteRepository
import com.sdk.domain.use_cases.*
import com.sdk.domain.use_cases.base.AllUseCases
import com.sdk.domain.use_cases.remote.*
import com.sdk.domain.use_cases.local.*
import com.sdk.kojetdsr.util.Constants
import com.sdk.kojetdsr.util.NetworkUtils
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
            .baseUrl(Constants.BASE_URL)
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
    fun provideWeatherRepository(dao: LocationNameDao, favoriteDao: FavoriteDao): WeatherLocalRepository {
        return WeatherLocalRepositoryImpl(dao, favoriteDao)
    }

    @Provides
    @Singleton
    fun provideWeatherRemoteRepository(service: WeatherService): WeatherRemoteRepository {
        return WeatherRemoteRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideAllUseCases(localRepo: WeatherLocalRepository, remoteRepo: WeatherRemoteRepository,): AllUseCases {
        return AllUseCases(
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(remoteRepo),
            saveLocationNameBaseUseCase = SaveLocationNameUseCase(localRepo),
            getLocationNamesUseCase = GetLocationNamesUseCase(localRepo),
            updateFavLocationName = UpdateFavLocationName(localRepo),
            saveFavoriteNameUseCase = SaveFavoriteNameUseCase(localRepo),
            getFavoriteNamesUseCase = GetFavoriteNamesUseCase(localRepo),
            deleteFavNameUseCase = DeleteFavNameUseCase(localRepo)
        )
    }
    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}