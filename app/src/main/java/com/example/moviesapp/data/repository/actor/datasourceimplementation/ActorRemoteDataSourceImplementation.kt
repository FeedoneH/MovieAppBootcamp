package com.example.moviesapp.data.repository.actor.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.repository.actor.datasource.ActorRemoteDataSource
import retrofit2.Response

class ActorRemoteDataSourceImplementation(val tmdbService: TMDBService):ActorRemoteDataSource {
    override suspend fun getActorCredits(id: String): Response<ActorCredits> {
       return tmdbService.getActorCreditsFromAPI(id)
    }

    override suspend fun getActorDetail(id: String): Response<ActorDetail> {
     return tmdbService.getActorDetailsFromAPI(id)
    }
}