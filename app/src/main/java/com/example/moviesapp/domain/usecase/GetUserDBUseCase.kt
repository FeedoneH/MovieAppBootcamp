package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.UserRepository

class GetUserDBUseCase(val userRepository: UserRepository) {
    suspend fun executeGetUserFromDB(uid:String) = userRepository.getUser(uid)
}