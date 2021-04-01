package com.example.moviesapp.presentation.actorDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetActorsRemoteUseCase
import com.example.moviesapp.presentation.details.DetailViewModel
import java.lang.IllegalArgumentException

class ActorDetailViewModelFactory(val getActorsRemoteUseCase: GetActorsRemoteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorDetailViewModel::class.java)) {
            return ActorDetailViewModel(getActorsRemoteUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}