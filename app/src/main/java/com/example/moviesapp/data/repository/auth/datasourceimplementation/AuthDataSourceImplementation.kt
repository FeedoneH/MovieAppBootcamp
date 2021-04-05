package com.example.moviesapp.data.repository.auth.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.repository.auth.datasource.AuthDataSource

import retrofit2.Response

class AuthDataSourceImplementation(val tmdbService: TMDBService) : AuthDataSource {
    override suspend fun getTokenId(): Response<TokenId> {
        return tmdbService.getTokenId()
    }

    override suspend fun getSessionId(requestToken: String): Response<SessionId>{
      return tmdbService.getSessionId(requestToken)


    }

    override suspend fun deleteSession(sessionId: String): Response<SignOut> {
        return tmdbService.deleteSessionId(sessionId)
    }
}