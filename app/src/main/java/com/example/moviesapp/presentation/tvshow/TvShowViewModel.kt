package com.example.moviesapp.presentation.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetTvShowsRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TvShowViewModel(val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase) : ViewModel() {
    var populartvShowList: MutableLiveData<Resource<TvShowList>> = MutableLiveData()
    var topRatedTvShowList: MutableLiveData<Resource<TvShowList>> = MutableLiveData()

    fun getPopularTvShows() = viewModelScope.launch(Dispatchers.IO) {
        populartvShowList.postValue(Resource.Loading())
        val response = getTvShowsRemoteUseCase.executeGetPopularTvShow()
        populartvShowList.postValue(response)
    }

    fun getTopRatedTvShows() = viewModelScope.launch(Dispatchers.IO) {
        topRatedTvShowList.postValue(Resource.Loading())
        val response = getTvShowsRemoteUseCase.executeGetTopRatedTvShow()
        topRatedTvShowList.postValue(response)
    }
}