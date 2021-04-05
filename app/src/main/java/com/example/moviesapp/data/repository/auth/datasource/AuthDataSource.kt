package com.example.moviesapp.data.repository.auth.datasource

import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId

import retrofit2.Response

interface AuthDataSource {
    suspend fun getTokenId(): Response<TokenId>
    suspend fun getSessionId(requestToken:String): Response<SessionId>
    suspend fun deleteSession(sessionId:String):Response<SignOut>
}