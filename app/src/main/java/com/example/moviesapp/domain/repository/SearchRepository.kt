package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.util.Resource

interface SearchRepository {
    suspend fun getSearchResultResource(query:String):Resource<Search>
}