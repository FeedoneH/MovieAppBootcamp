package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AuthRepository

class LogInUserUseCase(private val authRepository: AuthRepository) {
    suspend fun executeLogIn(email: String, password: String) = authRepository.signInUser(email, password)
}