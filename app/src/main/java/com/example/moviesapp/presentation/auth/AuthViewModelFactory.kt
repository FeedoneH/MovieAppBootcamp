package com.example.moviesapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.*
import java.lang.IllegalArgumentException

class AuthViewModelFactory(val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}