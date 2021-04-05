package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.util.Resource

interface Auth1Repository {
    suspend fun getTokenId(): Resource<TokenId>
    suspend fun getSessionId(requestToken: String): Resource<SessionId>
    suspend fun deleteSessionId(sessionId: String): Resource<SignOut>

}