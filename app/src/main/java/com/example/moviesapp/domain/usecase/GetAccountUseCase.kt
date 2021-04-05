package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AccountRepository

class GetAccountUseCase(val repository: AccountRepository) {
    suspend fun execute(sessionId: String) = repository.getAccountDetails(sessionId)
}