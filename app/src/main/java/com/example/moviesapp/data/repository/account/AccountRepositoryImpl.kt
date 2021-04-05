package com.example.moviesapp.data.repository.account

import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.repository.account.datasource.AccountRemoteDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.AccountRepository
import retrofit2.Response

class AccountRepositoryImpl(val accountRemoteDataSource: AccountRemoteDataSource) : AccountRepository {
    override suspend fun getAccountDetails(sessionId: String): Resource<Account> {
        return convertToResource(accountRemoteDataSource.getAccountDetail(sessionId))
    }

    override suspend fun getFavoriteMovies(accountId: String, sessionId: String): Resource<MovieList> {
        return convertToResource(accountRemoteDataSource.getFavoriteMovies(accountId, sessionId))
    }

    override suspend fun getFavoriteTvShows(accountId: String, sessionId: String): Resource<TvShowList> {
        return convertToResource(accountRemoteDataSource.getFavoriteTvShows(accountId, sessionId))
    }

    fun <T> convertToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}