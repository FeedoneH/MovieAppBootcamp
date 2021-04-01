package com.example.moviesapp.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import com.example.moviesapp.domain.usecase.GetTvShowsRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailViewModel(
        val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase,
        val getMoviesRemoteUseCase: GetMoviesRemoteUseCase)
    : ViewModel() {
    var tvShowDetail: MutableLiveData<Resource<TvShowDetail>> = MutableLiveData()
    var tvShowCredits: MutableLiveData<Resource<Credits>> = MutableLiveData()
    var movieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    var movieCredits: MutableLiveData<Resource<Credits>> = MutableLiveData()
    fun getTvShowDetail(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            tvShowDetail.postValue(Resource.Loading())
            val response = getTvShowsRemoteUseCase.executeGetTvShowDetail(id)
            tvShowDetail.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getTvShowCredits(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            tvShowCredits.postValue(Resource.Loading())
            val response = getTvShowsRemoteUseCase.executeGetTvShowCredits(id)
            tvShowCredits.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getMovieDetail(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            movieDetail.postValue(Resource.Loading())
            val response = getMoviesRemoteUseCase.executeMovieDetail(id)
            movieDetail.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getMovieCredits(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            movieCredits.postValue(Resource.Loading())
            val response = getMoviesRemoteUseCase.executeMovieCredits(id)
            movieCredits.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}