package com.example.moviesapp.data.repository.auth

import android.util.Log
import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.repository.auth.datasource.AuthDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.Auth1Repository


class AuthRepositoryImpl(val authDataSource: AuthDataSource) : Auth1Repository {
    override suspend fun getTokenId(): Resource<TokenId> {
        var response = authDataSource.getTokenId()
        if (response.isSuccessful) {
            var data = response.body()
            data?.let {
                return Resource.Success(it)
            }

        }
        return Resource.Loading()
    }

    override suspend fun getSessionId(requestToken: String): Resource<SessionId> {

        var response = authDataSource.getSessionId(requestToken)
        if (response.isSuccessful) {
            var data = response.body()!!
            return data.let {
                Resource.Success(it) }
        }
        return Resource.Error("Error occured")
    }

    override suspend fun deleteSessionId(sessionId: String):Resource<SignOut> {
        var response = authDataSource.deleteSession(sessionId)
        if (response.isSuccessful) {
            var data = response.body()!!
            Log.i("", "deleteSessionId: $data")
            return data.let { Resource.Success(it) }
        }
        return Resource.Error(response.message())
    }

}