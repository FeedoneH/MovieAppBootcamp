package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AccountRepository

class GetMovieFavoriteListUseCase(val repository: AccountRepository) {
    suspend fun execute(accountId: String, sessionId: String) = repository.getFavoriteMovies(accountId, sessionId)
}