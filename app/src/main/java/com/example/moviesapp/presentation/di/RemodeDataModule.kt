package com.example.moviesapp.presentation.di

import com.example.moviesapp.data.api.GoogleMapService
import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.repository.account.datasource.AccountRemoteDataSource
import com.example.moviesapp.data.repository.actor.datasource.ActorRemoteDataSource
import com.example.moviesapp.data.repository.account.datasourceimplementation.AccountRemoteDataSourceImplementation
import com.example.moviesapp.data.repository.actor.datasourceimplementation.ActorRemoteDataSourceImplementation
import com.example.moviesapp.data.repository.auth.datasource.AuthDataSource
import com.example.moviesapp.data.repository.auth.datasourceimplementation.AuthDataSourceImplementation
import com.example.moviesapp.data.repository.favorite.datasource.FavoriteDataSource
import com.example.moviesapp.data.repository.favorite.datasourceimplementation.FavoriteDataSourceImplementation
import com.example.moviesapp.data.repository.map.mapdatasource.GoogleMapDataSource
import com.example.moviesapp.data.repository.map.mapdatasourceimplementation.GoogleMapDataSourceImplementation
import com.example.moviesapp.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviesapp.data.repository.movie.datasourceimplementation.MovieRemoteDataSourceImplementation
import com.example.moviesapp.data.repository.search.datasource.SearchDataSource
import com.example.moviesapp.data.repository.search.datasourceimplementation.SearchDataSourcemplementation
import com.example.moviesapp.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.moviesapp.data.repository.tvshow.datasourceimplementation.TvShowRemoteDataSourceImplementation
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
    fun provideAccountRemoteData(tmdbService: TMDBService): AccountRemoteDataSource {
        return AccountRemoteDataSourceImplementation(tmdbService)
    }

    @Singleton
    @Provides
    fun provideGoogleMapData(googleMapService: GoogleMapService): GoogleMapDataSource {
        return GoogleMapDataSourceImplementation(googleMapService)
    }

    @Singleton
    @Provides
    fun provideAuthData(tmdbService: TMDBService): AuthDataSource {
        return AuthDataSourceImplementation(tmdbService)
    }
    @Singleton
    @Provides
    fun provideFavoriteRemoteData(tmdbService: TMDBService): FavoriteDataSource {
        return FavoriteDataSourceImplementation(tmdbService)
    }
}