package com.example.moviesapp.data.repository.favorite.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.repository.favorite.datasource.FavoriteDataSource
import retrofit2.Response

class FavoriteDataSourceImplementation(val tmdbService: TMDBService): FavoriteDataSource {
    override suspend fun toggleFavorite(body: FavoriteReuqestBody, accountId: String,sessionId: String): Response<PostResponse> {
      return tmdbService.setAsFavorite(body,accountId,sessionId)
    }
}