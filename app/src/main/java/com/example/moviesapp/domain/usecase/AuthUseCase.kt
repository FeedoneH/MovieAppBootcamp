package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.Auth1Repository

class AuthUseCase(val repository: Auth1Repository) {
    suspend fun executeAuth():Resource<TokenId> = repository.getTokenId()
    suspend fun getSessionID(requestToken:String) = repository.getSessionId(requestToken)

}