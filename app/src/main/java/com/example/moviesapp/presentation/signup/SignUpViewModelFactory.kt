package com.example.moviesapp.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.*
import java.lang.IllegalArgumentException

class SignUpViewModelFactory(val signUpUserUseCase: SignUpUserUseCase, val signOutUserUseCase: SignOutUserUseCase,val addUserDBUseCase: AddUserDBUseCase, val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(signUpUserUseCase, signOutUserUseCase,addUserDBUseCase, getCurrentUserUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}