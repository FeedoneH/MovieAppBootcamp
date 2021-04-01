package com.example.moviesapp.data.repository.movie.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImplementation(private val tmdbService: TMDBService) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(): Response<MovieList> = tmdbService.getPopularMoviesFromAPI()
    override suspend fun getNowPlayingMovies(): Response<MovieList> = tmdbService.getNowPlayingMoviesFromAPI()
    override suspend fun getMovieDetail(id: String): Response<MovieDetail> = tmdbService.getMovieDetailFromAPI(id)
    override suspend fun getMovieCredits(id: String): Response<Credits> =tmdbService.getMovieCreditsFromAPI(id)

}