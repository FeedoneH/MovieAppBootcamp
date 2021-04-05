package com.example.moviesapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.AuthUseCase
import com.example.moviesapp.domain.usecase.DeleteSessionUseCase
import java.lang.IllegalArgumentException

class LogInViewModelFactory(val authUseCase: AuthUseCase,val deleteSessionUseCase: DeleteSessionUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(authUseCase,deleteSessionUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}