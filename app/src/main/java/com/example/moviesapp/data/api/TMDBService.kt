package com.example.moviesapp.data.api

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.search.Search
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.*
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
    suspend fun getSearchResult(@Query("query") query: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<Search>

    @GET("authentication/token/new")
    suspend fun getTokenId(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TokenId>

    @FormUrlEncoded
    @POST("authentication/session/new")
    suspend fun getSessionId(@Field("request_token") request_token: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<SessionId>

    @GET("account")
    suspend fun getAccountDetails(@Query("session_id") sessionId: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<Account>

    @DELETE("authentication/session")
    suspend fun deleteSessionId(@Query("session_id") sessionId: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<SignOut>

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("account/{account_id}/favorite")
    suspend fun setAsFavorite(@Body body: FavoriteReuqestBody,
                              @Path("account_id") accountId: String,
                              @Query("session_id") sessionId: String,
                              @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<PostResponse>


    @FormUrlEncoded
    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(@Field("value") value: Number,
                          @Path("movie_id") movieId: Int,
                          @Query("session_id") sessionId: String,
                          @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<PostResponse>


    @FormUrlEncoded
    @POST("tv/{tv_id}/rating")
    suspend fun rateTvShow(@Field("value") value: Number,
                           @Path("tv_id") tvshowId: Int,
                           @Query("session_id") sessionId: String,
                           @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<PostResponse>

    @GET("movie/{movie_id}/account_states")
    suspend fun getMovieState(@Path("movie_id") movieId: Int,
                              @Query("session_id") sessionId: String,
                              @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MediaStatus>

    @GET("tv/{tv_id}/account_states")
    suspend fun getTvShowState(@Path("tv_id") tvshowId: Int,
                               @Query("session_id") sessionId: String,
                               @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MediaStatus>


    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(@Path("account_id") accountId: String,
                                  @Query("session_id") sessionId: String,
                                  @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MovieList>

    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavoriteTvShows(@Path("account_id") accountId: String,
                                   @Query("session_id") sessionId: String,
                                   @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TvShowList>

}