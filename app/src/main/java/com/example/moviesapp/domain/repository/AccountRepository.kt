package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.util.Resource
import retrofit2.Response

interface AccountRepository {
    suspend fun getAccountDetails(sessionId: String):Resource<Account>
    suspend fun getFavoriteMovies(accountId: String,sessionId: String,): Resource<MovieList>
    suspend fun getFavoriteTvShows(accountId: String,sessionId: String,): Resource<TvShowList>
}