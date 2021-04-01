package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.domain.repository.UserRepository

class AddUserDBUseCase(val userRepository: UserRepository) {
    suspend fun executeAddUser(user: User)=userRepository.addUser(user)
}