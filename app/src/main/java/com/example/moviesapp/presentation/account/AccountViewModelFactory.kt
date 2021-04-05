package com.example.moviesapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.*
import java.lang.IllegalArgumentException

class AccountViewModelFactory(val getAccountUseCase: GetAccountUseCase,
                              val deleteSessionUseCase: DeleteSessionUseCase,
                              val getMovieFavoriteListUseCase: GetMovieFavoriteListUseCase,
                              val getTvShowFavoriteListUseCase: GetTvShowFavoriteListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(getAccountUseCase, deleteSessionUseCase,
                    getMovieFavoriteListUseCase ,getTvShowFavoriteListUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}