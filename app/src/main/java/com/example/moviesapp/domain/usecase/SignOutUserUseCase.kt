package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.AuthRepository

class SignOutUserUseCase(private val authRepository: AuthRepository) {
    suspend fun executeSignOut() = authRepository.signOutUser()
}