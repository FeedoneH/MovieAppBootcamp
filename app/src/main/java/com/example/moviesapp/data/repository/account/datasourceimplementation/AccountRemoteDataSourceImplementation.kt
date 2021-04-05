package com.example.moviesapp.data.repository.account.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.repository.account.datasource.AccountRemoteDataSource
import retrofit2.Response

class AccountRemoteDataSourceImplementation(val tmdbService: TMDBService): AccountRemoteDataSource {
    override suspend fun getAccountDetail(sessionId: String): Response<Account> {
        return tmdbService.getAccountDetails(sessionId)
    }

    override suspend fun getFavoriteMovies(accountId: String, sessionId: String): Response<MovieList> {
        return tmdbService.getFavoriteMovies(accountId, sessionId)
    }

    override suspend fun getFavoriteTvShows(accountId: String, sessionId: String): Response<TvShowList> {
     return tmdbService.getFavoriteTvShows(accountId, sessionId)
    }


}