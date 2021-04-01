package com.example.moviesapp.data.repository.search

import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.repository.search.datasource.SearchDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.SearchRepository

class SearchRepositoryImpl(val searchDataSource: SearchDataSource):SearchRepository {
    override suspend fun getSearchResultResource(query:String): Resource<Search> {
        val response = searchDataSource.getSearchResult(query)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}