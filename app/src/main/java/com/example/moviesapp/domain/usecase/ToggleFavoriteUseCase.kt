package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.domain.repository.FavoriteMediaRepository

class ToggleFavoriteUseCase(val repository:FavoriteMediaRepository) {
    suspend fun  execute(body: FavoriteReuqestBody, accountId: String,sessionId: String)= repository.toggleFavorite(body,accountId,sessionId)
}