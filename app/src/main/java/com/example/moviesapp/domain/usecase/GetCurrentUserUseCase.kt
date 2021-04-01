package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AuthRepository
import com.example.moviesapp.domain.repository.UserRepository

class GetCurrentUserUseCase(val authRepository: AuthRepository) {
    suspend fun executGetUsser() = authRepository.getCurrentUser()
}