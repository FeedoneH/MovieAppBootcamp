package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.util.Resource

interface ActorRepository {
suspend fun getActorDetail(id:String): Resource<ActorDetail>
suspend fun getActorCredits(id: String): Resource<ActorCredits>

}