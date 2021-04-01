package com.example.moviesapp.data.repository.actor.datasource

import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import retrofit2.Response

interface ActorRemoteDataSource {
    suspend fun getActorCredits(id:String):Response<ActorCredits>
    suspend fun getActorDetail(id:String):Response<ActorDetail>
}