package com.example.moviesapp.data.repository.account.datasource

import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.tvshow.TvShowList
import retrofit2.Response

interface AccountRemoteDataSource {
    suspend fun getAccountDetail(sessionId: String): Response<Account>
    suspend fun getFavoriteMovies(accountId: String,sessionId: String,):Response<MovieList>
    suspend fun getFavoriteTvShows(accountId: String,sessionId: String,):Response<TvShowList>
}
