package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.MovieRepository

class GetMovieStatusUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, sessionId: String) = repository.getMovieState(movieId, sessionId)
}