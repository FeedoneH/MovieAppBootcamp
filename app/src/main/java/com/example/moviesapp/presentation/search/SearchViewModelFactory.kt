package com.example.moviesapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetMoviesRemoteUseCase
import com.example.moviesapp.domain.usecase.GetSearchResultRemoteUseCase
import java.lang.IllegalArgumentException

class SearchViewModelFactory(private val getSearchResultRemoteUseCase: GetSearchResultRemoteUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(getSearchResultRemoteUseCase) as  T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}