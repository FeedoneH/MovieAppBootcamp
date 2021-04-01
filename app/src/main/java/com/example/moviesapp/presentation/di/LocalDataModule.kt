package com.example.moviesapp.presentation.di

import com.example.moviesapp.data.db.UserDao
import com.example.moviesapp.data.repository.user.datasource.UserDatabaseDataSource
import com.example.moviesapp.data.repository.user.datasourceimplementation.UserDatabaseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideUserLocalDataModule(userDao: UserDao):UserDatabaseDataSource{
        return UserDatabaseDataSourceImpl(userDao)
    }
}