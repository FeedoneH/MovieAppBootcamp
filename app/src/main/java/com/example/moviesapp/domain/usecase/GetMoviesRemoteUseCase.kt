package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.MovieRepository

class GetMoviesRemoteUseCase(private val repository: MovieRepository) {
    suspend fun executePopularMovies():Resource<MovieList>  = repository.getPopularMovies()
    suspend fun executeNowPlayingMovies():Resource<MovieList>  = repository.getNowPlayingMovies()
    suspend fun executeMovieDetail(id:String):Resource<MovieDetail> = repository.getMovieDetail(id)
    suspend fun executeMovieCredits(id:String):Resource<Credits> = repository.getMovieCredits(id)
}