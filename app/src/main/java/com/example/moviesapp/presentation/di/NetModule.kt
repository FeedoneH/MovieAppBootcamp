package com.example.moviesapp.presentation.di

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.api.GoogleMapService
import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.Rated
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import javax.inject.Named
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class NetModule {

    @Singleton
    @Provides
    @Named("tmdb")
    fun provideNetModule(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())

                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    @Named("google")
    fun provideGoogleRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.GOOGLE_BASE_URL)
                .client(okHttpClient)
                .build()
    }


    @Singleton
    @Provides
    fun provideApiService(@Named("tmdb") retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }


    @Singleton
    @Provides
    fun provideGoogleMapApiService(@Named("google") retrofit: Retrofit): GoogleMapService {
        return retrofit.create(GoogleMapService::class.java)
    }

    @Singleton
    @Provides
    fun provideGoogleHttpOk(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

}