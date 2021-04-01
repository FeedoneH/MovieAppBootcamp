package com.example.moviesapp.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.movie.Movie
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import com.example.moviesapp.domain.usecase.GetSearchResultRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val getSearchResultRemoteUseCase: GetSearchResultRemoteUseCase) : ViewModel() {
    var searchList: MutableLiveData<Resource<Search>> = MutableLiveData()

    fun getSearchList(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchList.postValue(Resource.Loading())

        try {
            val responseSearchList = getSearchResultRemoteUseCase.executeSearch(query)
            searchList.postValue(responseSearchList)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}