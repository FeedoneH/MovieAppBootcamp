package com.example.moviesapp.presentation.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.moviesapp.MainActivity
import com.example.moviesapp.data.db.UserDao
import com.example.moviesapp.data.db.UserDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(@ApplicationContext context: Context): UserDataBase {

        return Room
                .databaseBuilder(context, UserDataBase::class.java, "user_data_table")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideSavedNewsDao(userDataBase: UserDataBase): UserDao {
        return userDataBase.userDao()
    }

}