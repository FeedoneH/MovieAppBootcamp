package com.example.moviesapp.presentation.di

import com.example.moviesapp.domain.repository.*
import com.example.moviesapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {


    @Singleton
    @Provides
    fun providePopularMovieGetUseCase(repository: MovieRepository): GetMoviesRemoteUseCase {
        return GetMoviesRemoteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideTvShowUseCase(repository: TvShowRepository): GetTvShowsRemoteUseCase {
        return GetTvShowsRemoteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideActorUseCase(repository: ActorRepository): GetActorsRemoteUseCase {
        return GetActorsRemoteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSearchUseCase(repository: SearchRepository): GetSearchResultRemoteUseCase {
        return GetSearchResultRemoteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(repository: UserRepository): GetUserDBUseCase {
        return GetUserDBUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddUserUseCase(repository: UserRepository): AddUserDBUseCase {
        return AddUserDBUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGoogleMapUseCase(repository: GoogleMapRepository): GetPlacesUseCase {
        return GetPlacesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAuthUseCase(repository: Auth1Repository): AuthUseCase {
        return AuthUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAccountUseCase(repository: AccountRepository): GetAccountUseCase {
        return GetAccountUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteSessionUseCase(repository: Auth1Repository): DeleteSessionUseCase {
        return DeleteSessionUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideToggleFavoriteUseCase(repository: FavoriteMediaRepository): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideRateMovieUseCase(repository: MovieRepository): RateMovieUseCase {
        return RateMovieUseCase(repository)
    }
  @Singleton
    @Provides
    fun provideRateTvShowUseCase(repository: TvShowRepository): RateTvShowUseCase {
        return RateTvShowUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMovieStatusUseCase(repository: MovieRepository): GetMovieStatusUseCase {
        return GetMovieStatusUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMovieFavoritesUseCase(repository: AccountRepository): GetMovieFavoriteListUseCase {
        return GetMovieFavoriteListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetTvShowFavoritesUseCase(repository: AccountRepository): GetTvShowFavoriteListUseCase {
        return GetTvShowFavoriteListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetTvShowtateUseCase(repository: TvShowRepository): GetTvShowStateUseCase {
        return GetTvShowStateUseCase(repository)
    }

}