package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.MovieRepository

class RateMovieUseCase(val repository:MovieRepository) {
    suspend fun execute(value: Number,movieId:Int, sessionId: String) = repository.rateMovie(value,movieId,sessionId)
}