package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class SignUpUserUseCase(val authRepository: AuthRepository) {
   suspend  fun executeSignUp(userName: String, email: String, password: String) = authRepository.signUpUser(userName, email, password)
}