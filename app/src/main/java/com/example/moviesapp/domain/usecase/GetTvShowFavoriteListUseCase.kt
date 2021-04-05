package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AccountRepository

class GetTvShowFavoriteListUseCase(val repository: AccountRepository) {
    suspend fun execute(accountId: String, sessionId: String) = repository.getFavoriteTvShows(accountId, sessionId)
}