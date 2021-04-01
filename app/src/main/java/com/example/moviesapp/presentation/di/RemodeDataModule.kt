package com.example.moviesapp.presentation.di

import com.example.moviesapp.data.api.GoogleMapService
import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.repository.actor.datasource.ActorRemoteDataSource
import com.example.moviesapp.data.repository.actor.datasourceimplementation.ActorRemoteDataSourceImplementation
import com.example.moviesapp.data.repository.auth.datasource.AuthFireBaseDataSource
import com.example.moviesapp.data.repository.auth.datasourceimplementation.AuthFireBaseDataSourceImpl
import com.example.moviesapp.data.repository.map.mapdatasource.GoogleMapDataSource
import com.example.moviesapp.data.repository.map.mapdatasourceimplementation.GoogleMapDataSourceImplementation
import com.example.moviesapp.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviesapp.data.repository.movie.datasourceimplementation.MovieRemoteDataSourceImplementation
import com.example.moviesapp.data.repository.search.datasource.SearchDataSource
import com.example.moviesapp.data.repository.search.datasourceimplementation.SearchDataSourcemplementation
import com.example.moviesapp.data.repository.tvshow.TvShowRepositoryImpl
import com.example.moviesapp.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.moviesapp.data.repository.tvshow.datasourceimplementation.TvShowRemoteDataSourceImplementation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RemodeDataModule {
    @Singleton
    @Provides
    fun provideMovieRemoteData(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImplementation(tmdbService)
    }

    @Singleton
    @Provides
    fun provideTVRemoteData(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImplementation(tmdbService)

    }

    @Singleton
    @Provides
    fun provideActorRemoteData(tmdbService: TMDBService): ActorRemoteDataSource {
        return ActorRemoteDataSourceImplementation(tmdbService)

    }

    @Singleton
    @Provides
    fun provideSearchRemoteData(tmdbService: TMDBService): SearchDataSource {
        return SearchDataSourcemplementation(tmdbService)

    }

    @Singleton
    @Provides
    fun provideSAuthData(firebaseAuth: FirebaseAuth): AuthFireBaseDataSource {
        return AuthFireBaseDataSourceImpl(firebaseAuth)

    }
    @Singleton
    @Provides
    fun provideGoogleMapData(googleMapService: GoogleMapService):GoogleMapDataSource{
        return GoogleMapDataSourceImplementation(googleMapService)
    }
}