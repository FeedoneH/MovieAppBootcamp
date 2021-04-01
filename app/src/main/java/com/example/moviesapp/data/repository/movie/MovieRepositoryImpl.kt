package com.example.moviesapp.data.repository.movie

import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.MovieRepository
import retrofit2.Response

class MovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository {
    override suspend fun getPopularMovies(): Resource<MovieList>  {
        return convertToResource(
                movieRemoteDataSource.getPopularMovies()

        )
    }

    override suspend fun getNowPlayingMovies(): Resource<MovieList> {
        return convertToResource(
                movieRemoteDataSource.getNowPlayingMovies()
        )
    }

    override suspend fun getMovieDetail(id: String): Resource<MovieDetail> {
       return convertDetailResponseToResource(
             movieRemoteDataSource.getMovieDetail(id)
        )
    }

    override suspend fun getMovieCredits(id: String): Resource<Credits> {
        var response = movieRemoteDataSource.getMovieCredits(id)
        if(response.isSuccessful){
            return Resource.Success(response.body()!!)
        }
        return Resource.Error(response.message())
    }

    fun convertDetailResponseToResource(response: Response<MovieDetail>): Resource<MovieDetail> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


    fun convertToResource(response: Response<MovieList>): Resource<MovieList> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}