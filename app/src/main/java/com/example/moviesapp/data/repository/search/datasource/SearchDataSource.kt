package com.example.moviesapp.data.repository.search.datasource

import com.example.moviesapp.data.model.search.Search
import retrofit2.Response

interface SearchDataSource {
    suspend fun getSearchResult(query:String): Response<Search>
}