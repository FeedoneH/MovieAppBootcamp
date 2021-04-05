package com.example.moviesapp.presentation.di


import com.example.moviesapp.domain.usecase.*
import com.example.moviesapp.presentation.map.MapViewModelFactory
import com.example.moviesapp.presentation.account.AccountViewModelFactory
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModelFactory
import com.example.moviesapp.presentation.details.DetailViewModelFactory
import com.example.moviesapp.presentation.login.LogInViewModelFactory
import com.example.moviesapp.presentation.movie.MovieViewModelFactory
import com.example.moviesapp.presentation.search.SearchViewModelFactory
import com.example.moviesapp.presentation.auth.AuthViewModelFactory
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
    fun provideMovieViewModelFactory(
            getMoviesRemoteUseCase: GetMoviesRemoteUseCase,
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesRemoteUseCase)
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
            getMoviesRemoteUseCase: GetMoviesRemoteUseCase,
            getMovieStatusUseCase: GetMovieStatusUseCase,
            getTvShowStateUseCase: GetTvShowStateUseCase,
            toggleFavoriteUseCase: ToggleFavoriteUseCase,
            rateMovieUseCase: RateMovieUseCase,
            rateTvShowUseCase: RateTvShowUseCase,
            getAccountUseCase: GetAccountUseCase,
    ): DetailViewModelFactory {
        return DetailViewModelFactory(getTvShowsRemoteUseCase, getMoviesRemoteUseCase,
                getMovieStatusUseCase, getTvShowStateUseCase, toggleFavoriteUseCase,
                rateMovieUseCase, rateTvShowUseCase, getAccountUseCase)
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
    fun provideLogInViewModelFactory(authUseCase: AuthUseCase, deleteSessionUseCase: DeleteSessionUseCase): LogInViewModelFactory {
        return LogInViewModelFactory(authUseCase, deleteSessionUseCase)
    }

    @Singleton
    @Provides
    fun provideSignUpViewModelFactory(authUseCase: AuthUseCase): AuthViewModelFactory {
        return AuthViewModelFactory(authUseCase)
    }

    @Singleton
    @Provides
    fun provideAccountViewModelFactory(getAccountUseCase: GetAccountUseCase,
                                       deleteSessionUseCase: DeleteSessionUseCase,
                                       getMovieFavoriteListUseCase: GetMovieFavoriteListUseCase,
                                       getTvShowFavoriteListUseCase: GetTvShowFavoriteListUseCase): AccountViewModelFactory {
        return AccountViewModelFactory(getAccountUseCase,
                deleteSessionUseCase,
                getMovieFavoriteListUseCase,
                getTvShowFavoriteListUseCase)
    }

    @Singleton
    @Provides
    fun provideMapViewModelFactory(getPlacesUseCase: GetPlacesUseCase): MapViewModelFactory {
        return MapViewModelFactory(getPlacesUseCase)
    }

}