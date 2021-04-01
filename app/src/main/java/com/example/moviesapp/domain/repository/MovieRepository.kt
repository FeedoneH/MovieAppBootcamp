package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.util.Resource


interface MovieRepository {
    suspend fun getPopularMovies(): Resource<MovieList>
    suspend fun getNowPlayingMovies(): Resource<MovieList>
    suspend fun getMovieDetail(id: String): Resource<MovieDetail>
    suspend fun getMovieCredits(id: String): Resource<Credits>
}