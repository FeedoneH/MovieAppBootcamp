package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.util.Resource

interface FavoriteMediaRepository {
    suspend fun toggleFavorite(body: FavoriteReuqestBody, accountId: String,sessionId: String):Resource<PostResponse>
}