package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.Auth1Repository

class DeleteSessionUseCase(val auth1Repository: Auth1Repository) {
    suspend fun execute(sessionId: String) = auth1Repository.deleteSessionId(sessionId)
}