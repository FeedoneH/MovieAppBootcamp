package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.SearchRepository

class GetSearchResultRemoteUseCase(val searchRepository: SearchRepository) {
    suspend fun executeSearch(query: String): Resource<Search> = searchRepository.getSearchResultResource(query)
}