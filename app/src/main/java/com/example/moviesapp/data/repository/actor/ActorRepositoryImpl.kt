package com.example.moviesapp.data.repository.actor

import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.repository.actor.datasource.ActorRemoteDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.ActorRepository

class ActorRepositoryImpl(val actorRemoteDataSource: ActorRemoteDataSource) : ActorRepository {
    override suspend fun getActorDetail(id: String): Resource<ActorDetail> {
        val response = actorRemoteDataSource.getActorDetail(id)
        if (response.isSuccessful) {
            return Resource.Success(response.body()!!)
        }
        return Resource.Error(response.message())
    }

    override suspend fun getActorCredits(id: String): Resource<ActorCredits> {
        val response = actorRemoteDataSource.getActorCredits(id)
        if (response.isSuccessful) {
            return Resource.Success(response.body()!!)
        }
        return Resource.Error(response.message())
    }
}