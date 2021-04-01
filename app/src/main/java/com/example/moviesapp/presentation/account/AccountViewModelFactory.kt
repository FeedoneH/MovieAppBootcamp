package com.example.moviesapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetCurrentUserUseCase
import com.example.moviesapp.domain.usecase.GetUserDBUseCase
import com.example.moviesapp.domain.usecase.SignOutUserUseCase
import java.lang.IllegalArgumentException

class AccountViewModelFactory(val getCurrentUserUseCase: GetCurrentUserUseCase,val getUserDBUseCase: GetUserDBUseCase, val signOutUserUseCase: SignOutUserUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(getCurrentUserUseCase,getUserDBUseCase, signOutUserUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}