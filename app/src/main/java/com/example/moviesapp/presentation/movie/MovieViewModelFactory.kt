package com.example.moviesapp.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetCurrentUserUseCase
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import java.lang.IllegalArgumentException

class MovieViewModelFactory(private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase,
                            private val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getMoviesRemoteUseCase, getCurrentUserUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}