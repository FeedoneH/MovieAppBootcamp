package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.ActorRepository

class GetActorsRemoteUseCase(private val repository: ActorRepository) {
    suspend fun executeGetActorDetail(id: String): Resource<ActorDetail> = repository.getActorDetail(id)
    suspend fun executeGetActorCredits(id: String): Resource<ActorCredits> = repository.getActorCredits(id)
}