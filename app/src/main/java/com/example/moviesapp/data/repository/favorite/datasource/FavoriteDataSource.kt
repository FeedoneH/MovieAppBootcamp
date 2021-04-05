package com.example.moviesapp.data.repository.favorite.datasource

import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import retrofit2.Response

interface FavoriteDataSource {
    suspend fun toggleFavorite(body: FavoriteReuqestBody,accountId: String,sessionId: String):Response<PostResponse>
}