package com.example.moviesapp.data.repository.search.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.repository.search.datasource.SearchDataSource
import retrofit2.Response

class SearchDataSourcemplementation(val tmdbService: TMDBService):SearchDataSource {
    override suspend fun getSearchResult(query:String): Response<Search> {
       return tmdbService.getSearchResult(query)
    }
}