package com.example.moviesapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetCurrentUserUseCase
import com.example.moviesapp.domain.usecase.LogInUserUseCase
import java.lang.IllegalArgumentException

class LogInViewModelFactory(val logInUserUseCase: LogInUserUseCase, val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(logInUserUseCase,getCurrentUserUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}