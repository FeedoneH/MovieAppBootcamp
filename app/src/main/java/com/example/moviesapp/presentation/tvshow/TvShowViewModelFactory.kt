package com.example.moviesapp.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetTvShowsRemoteUseCase
import com.example.moviesapp.presentation.movie.MovieViewModel
import java.lang.IllegalArgumentException

class TvShowViewModelFactory(val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(getTvShowsRemoteUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}