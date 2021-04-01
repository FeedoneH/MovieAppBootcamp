package com.example.moviesapp.data.api

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import dagger.Module
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Named


@Named("tmdb")
interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMoviesFromAPI(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MovieList>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesFromAPI(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShowsFromAPI(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TvShowList>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShowsFromAPI(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TvShowList>

    @GET("tv/{id}")
    suspend fun getTvShowDetailFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TvShowDetail>

    @GET("movie/{id}")
    suspend fun getMovieDetailFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MovieDetail>

    @GET("movie/{id}/credits")
    suspend fun getMovieCreditsFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<Credits>

    @GET("tv/{id}/credits")
    suspend fun getTvShowCreditsFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<Credits>

    @GET("person/{id}")
    suspend fun getActorDetailsFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<ActorDetail>

    @GET("person/{id}/combined_credits")
    suspend fun getActorCreditsFromAPI(@Path("id") id: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<ActorCredits>

    @GET("search/multi")
    suspend fun getSearchResult(@Query("query") query: String,@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<Search>

}