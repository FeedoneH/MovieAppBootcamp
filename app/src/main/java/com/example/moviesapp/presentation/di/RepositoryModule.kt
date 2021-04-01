package com.example.moviesapp.presentation.di

import com.example.moviesapp.data.db.UserDao
import com.example.moviesapp.data.repository.actor.ActorRepositoryImpl
import com.example.moviesapp.data.repository.actor.datasource.ActorRemoteDataSource
import com.example.moviesapp.data.repository.auth.AuthFBRepositoryImpl
import com.example.moviesapp.data.repository.auth.datasource.AuthFireBaseDataSource
import com.example.moviesapp.data.repository.auth.datasourceimplementation.AuthFireBaseDataSourceImpl
import com.example.moviesapp.data.repository.map.GoogleMapRepositoryImpl
import com.example.moviesapp.data.repository.map.mapdatasource.GoogleMapDataSource
import com.example.moviesapp.data.repository.movie.MovieRepositoryImpl
import com.example.moviesapp.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviesapp.data.repository.search.SearchRepositoryImpl
import com.example.moviesapp.data.repository.search.datasource.SearchDataSource
import com.example.moviesapp.data.repository.tvshow.TvShowRepositoryImpl
import com.example.moviesapp.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.moviesapp.data.repository.user.UserRepositoryImpl
import com.example.moviesapp.data.repository.user.datasource.UserDatabaseDataSource
import com.example.moviesapp.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepositoryModule(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideTvShowRepositoryModule(tvShowRemoteDataSource: TvShowRemoteDataSource): TvShowRepository {
        return TvShowRepositoryImpl(tvShowRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideActorRepositoryModule(actorRemoteDataSource: ActorRemoteDataSource): ActorRepository {
        return ActorRepositoryImpl(actorRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideSearchRepositoryModule(searchDataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(searchDataSource)
    }

    @Singleton
    @Provides
    fun provideUserRepositoryModule(userDatabaseDataSource: UserDatabaseDataSource): UserRepository {
        return UserRepositoryImpl(userDatabaseDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthRepositoryModule(authFireBaseDataSource: AuthFireBaseDataSource): AuthRepository {
        return AuthFBRepositoryImpl(authFireBaseDataSource)
    }

    @Singleton
    @Provides
    fun provideGoogleMapRepository(googleMapDataSource: GoogleMapDataSource): GoogleMapRepository {
        return GoogleMapRepositoryImpl(googleMapDataSource)
    }
}