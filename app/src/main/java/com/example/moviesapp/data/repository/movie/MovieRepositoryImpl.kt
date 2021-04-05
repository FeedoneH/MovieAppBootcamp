package com.example.moviesapp.data.repository.movie

import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.apiResponse.MediaStatus
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
       return convertToResource(
             movieRemoteDataSource.getMovieDetail(id)
        )
    }

    override suspend fun getMovieCredits(id: String): Resource<Credits> {
       return convertToResource(
               movieRemoteDataSource.getMovieCredits(id)
       )
    }

    override suspend fun rateMovie(value: Number,movieId:Int, sessionId: String): Resource<PostResponse> {
      return convertToResource(
              movieRemoteDataSource.rateMovie(value,movieId,sessionId)
      )
    }

    override suspend fun getMovieState(movieId: Int, sessionId: String): Resource<MediaStatus> {
        return convertToResource(
                movieRemoteDataSource.getMovieState(movieId,sessionId)
        )
    }

    fun <T> convertToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


}