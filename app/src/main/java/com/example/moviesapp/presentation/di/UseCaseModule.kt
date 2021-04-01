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
    fun provideGetAuthUseCase(repository: AuthRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddUserUseCase(repository: UserRepository): AddUserDBUseCase {
        return AddUserDBUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLogInUserUseCase(repository: AuthRepository): LogInUserUseCase {
        return LogInUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignUpUserUseCase(repository: AuthRepository): SignUpUserUseCase {
        return SignUpUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignOutUserUseCase(repository: AuthRepository): SignOutUserUseCase {
        return SignOutUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGoogleMapUseCase(repository: GoogleMapRepository): GetPlacesUseCase {
        return GetPlacesUseCase(repository)
    }
}