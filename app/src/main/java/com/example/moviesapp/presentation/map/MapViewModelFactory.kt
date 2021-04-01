package com.example.moviesapp.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.usecase.GetPlacesUseCase
import java.lang.IllegalArgumentException

class MapViewModelFactory(private val getPlacesUseCase: GetPlacesUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(getPlacesUseCase) as T;
        }
        throw IllegalArgumentException("unacceptable argument")
    }
}