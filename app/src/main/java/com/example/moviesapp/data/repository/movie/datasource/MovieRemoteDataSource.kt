package com.example.moviesapp.data.repository.movie.datasource

import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): Response<MovieList>
    suspend fun getNowPlayingMovies(): Response<MovieList>
    suspend fun getMovieDetail(id: String): Response<MovieDetail>
    suspend fun getMovieCredits(id: String): Response<Credits>
    suspend fun rateMovie(value: Number,movieId: Int, sessionId: String): Response<PostResponse>
    suspend fun getMovieState(movieId: Int, sessionId: String):Response<MediaStatus>

}