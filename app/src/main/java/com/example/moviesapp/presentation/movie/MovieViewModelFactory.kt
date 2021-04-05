package com.example.moviesapp.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetAccountUseCase
import com.example.moviesapp.domain.usecase.GetMovieStatusUseCase
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import com.example.moviesapp.domain.usecase.ToggleFavoriteUseCase
import java.lang.IllegalArgumentException

class MovieViewModelFactory(private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase,

                            ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getMoviesRemoteUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}