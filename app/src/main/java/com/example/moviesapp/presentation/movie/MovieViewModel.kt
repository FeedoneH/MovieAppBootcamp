package com.example.moviesapp.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase) : ViewModel() {
    var popularMoviesList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    var nowPlayingMoviesList: MutableLiveData<Resource<MovieList>> = MutableLiveData()


    fun getMoviesList() = viewModelScope.launch(Dispatchers.IO) {
        popularMoviesList.postValue(Resource.Loading())

        try {
            val responsePopularMovieList = getMoviesRemoteUseCase.executePopularMovies()
            popularMoviesList.postValue(responsePopularMovieList)


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getnowPlaying() = viewModelScope.launch(Dispatchers.IO) {
        nowPlayingMoviesList.postValue(Resource.Loading())
        try {
            val responseNowPlayingMovieList = getMoviesRemoteUseCase.executeNowPlayingMovies()
            nowPlayingMoviesList.postValue(responseNowPlayingMovieList)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



}