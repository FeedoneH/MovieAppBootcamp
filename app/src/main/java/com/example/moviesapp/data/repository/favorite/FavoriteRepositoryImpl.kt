package com.example.moviesapp.data.repository.favorite

import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.repository.favorite.datasource.FavoriteDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.FavoriteMediaRepository

class FavoriteRepositoryImpl(val favoriteDataSource: FavoriteDataSource):FavoriteMediaRepository {
    override suspend fun toggleFavorite(body: FavoriteReuqestBody, accountId: String,sessionId: String): Resource<PostResponse> {
   var res = favoriteDataSource.toggleFavorite(body, accountId,sessionId)
     if(res.isSuccessful){
       res.body()?.let {
          return Resource.Success(it) }
     }
        return Resource.Error(res.message())
    }
}