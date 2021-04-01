package com.example.moviesapp.presentation.di


import com.example.moviesapp.domain.usecase.*
import com.example.moviesapp.presentation.map.MapViewModelFactory
import com.example.moviesapp.presentation.account.AccountViewModelFactory
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModelFactory
import com.example.moviesapp.presentation.details.DetailViewModelFactory
import com.example.moviesapp.presentation.login.LogInViewModelFactory
import com.example.moviesapp.presentation.movie.MovieViewModelFactory
import com.example.moviesapp.presentation.search.SearchViewModelFactory
import com.example.moviesapp.presentation.signup.SignUpViewModelFactory
import com.example.moviesapp.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideMovieViewModelFactory(getMoviesRemoteUseCase: GetMoviesRemoteUseCase,getCurrentUserUseCase: GetCurrentUserUseCase): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesRemoteUseCase,getCurrentUserUseCase)
    }

    @Singleton
    @Provides
    fun provideTvShowViewModelFactory(getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowsRemoteUseCase)
    }

    @Singleton
    @Provides
    fun provideDetailViewModelFactory(
            getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase,
            getMoviesRemoteUseCase: GetMoviesRemoteUseCase): DetailViewModelFactory {
        return DetailViewModelFactory(getTvShowsRemoteUseCase, getMoviesRemoteUseCase)
    }

    @Singleton
    @Provides
    fun provideActorDetailViewModelFactory(getActorsRemoteUseCase: GetActorsRemoteUseCase): ActorDetailViewModelFactory {
        return ActorDetailViewModelFactory(getActorsRemoteUseCase)
    }

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(getSearchResultRemoteUseCase: GetSearchResultRemoteUseCase): SearchViewModelFactory {
        return SearchViewModelFactory(getSearchResultRemoteUseCase)
    }

    @Singleton
    @Provides
    fun provideLogInViewModelFactory(logInUserUseCase: LogInUserUseCase,getCurrentUserUseCase: GetCurrentUserUseCase): LogInViewModelFactory {
        return LogInViewModelFactory(logInUserUseCase,getCurrentUserUseCase)
    }

    @Singleton
    @Provides
    fun provideSignUpViewModelFactory(signUpUserUseCase: SignUpUserUseCase,
                                      signOutUserUseCase: SignOutUserUseCase,
                                      addUserDBUseCase: AddUserDBUseCase,
                                      getCurrentUserUseCase: GetCurrentUserUseCase): SignUpViewModelFactory {
        return SignUpViewModelFactory(signUpUserUseCase, signOutUserUseCase, addUserDBUseCase, getCurrentUserUseCase)
    }

    @Singleton
    @Provides
    fun provideAccountViewModelFactory(getCurrentUserUseCase: GetCurrentUserUseCase, getUserDBUseCase: GetUserDBUseCase, signOutUserUseCase: SignOutUserUseCase): AccountViewModelFactory {
        return AccountViewModelFactory(getCurrentUserUseCase, getUserDBUseCase, signOutUserUseCase)
    }

    @Singleton
    @Provides
    fun provideMapViewModelFactory(getPlacesUseCase: GetPlacesUseCase): MapViewModelFactory {
        return MapViewModelFactory(getPlacesUseCase)
    }

}