package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.TvShowRepository

class RateTvShowUseCase(val repository: TvShowRepository) {
    suspend fun execute(value: Number, tvShowId: Int, sessionId: String) = repository.rateTvShow(value, tvShowId, sessionId)
}