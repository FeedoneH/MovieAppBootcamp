package com.example.moviesapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.*
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
        private val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase,
        private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase,
        private val getMovieStatusUseCase: GetMovieStatusUseCase,
        private val getTvShowStateUseCase: GetTvShowStateUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
        private val rateMovieUseCase: RateMovieUseCase,
        private val rateTvShowUseCase: RateTvShowUseCase,
        private val getAccountUseCase: GetAccountUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(getTvShowsRemoteUseCase,
                    getMoviesRemoteUseCase,
                    getMovieStatusUseCase,
                    getTvShowStateUseCase,
                    toggleFavoriteUseCase,
                    rateMovieUseCase,
                    rateTvShowUseCase,
                    getAccountUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}