package com.example.moviesapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import com.example.moviesapp.domain.usecase.GetTvShowsRemoteUseCase
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
        val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase,
        val getMoviesRemoteUseCase: GetMoviesRemoteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(getTvShowsRemoteUseCase, getMoviesRemoteUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}