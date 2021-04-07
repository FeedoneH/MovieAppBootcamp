package com.example.moviesapp.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.model.movie.MovieDetail
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.Rated
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class DetailViewModel(
        private val getTvShowsRemoteUseCase: GetTvShowsRemoteUseCase,
        private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase,
        private val getMovieStatusUseCase: GetMovieStatusUseCase,
        private val getTvShowStateUseCase: GetTvShowStateUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
        private val rateMovieUseCase: RateMovieUseCase,
        private val rateTvShowUseCase: RateTvShowUseCase,
        private val getAccountUseCase: GetAccountUseCase,
) : ViewModel() {
    var tvShowDetail: MutableLiveData<Resource<TvShowDetail>> = MutableLiveData()
    var tvShowCredits: MutableLiveData<Resource<Credits>> = MutableLiveData()
    var movieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    var movieCredits: MutableLiveData<Resource<Credits>> = MutableLiveData()
    var movieState: MutableLiveData<Resource<MediaStatus>> = MutableLiveData()
    var tvshowState: MutableLiveData<Resource<MediaStatus>> = MutableLiveData()
    var toggleFavResponse: MutableLiveData<Resource<PostResponse>> = MutableLiveData()
    var authInfo: MutableLiveData<Resource<Account>> = MutableLiveData()
    var movieRate: MutableLiveData<Resource<PostResponse>> = MutableLiveData()
    var movieRating: MutableLiveData<Int> = MutableLiveData()
    var tvShowRate: MutableLiveData<Resource<PostResponse>> = MutableLiveData()
    fun getAuthInfo(sessionId: String) = viewModelScope.launch (Dispatchers.IO) {
        authInfo.postValue(Resource.Loading())
        try {
            var user = getAccountUseCase.execute(sessionId)
            authInfo.postValue(user)
        } catch (e: Exception) {
            authInfo.postValue(e.message?.let { Resource.Error(it) })
        }
    }

    fun getTvShowDetail(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            tvShowDetail.postValue(Resource.Loading())
            val response = getTvShowsRemoteUseCase.executeGetTvShowDetail(id)
            tvShowDetail.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getTvShowCredits(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            tvShowCredits.postValue(Resource.Loading())
            val response = getTvShowsRemoteUseCase.executeGetTvShowCredits(id)
            tvShowCredits.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMovieDetail(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            movieDetail.postValue(Resource.Loading())
            val response = getMoviesRemoteUseCase.executeMovieDetail(id)
            movieDetail.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMovieCredits(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            movieCredits.postValue(Resource.Loading())
            val response = getMoviesRemoteUseCase.executeMovieCredits(id)
            movieCredits.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMovieState(movieId: Int, sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        movieState.postValue(Resource.Loading())
        try {
            var response = getMovieStatusUseCase.execute(movieId, sessionId)
            movieState.postValue(response)
           if( response.data?.rated is Rated) {
               movieRating.postValue((response.data!!.rated as Rated)?.value)
           }
        } catch (e: Exception) {
            e.printStackTrace()
            var response = getMovieStatusUseCase.execute(movieId, sessionId)
            if (e.message!!.isNotEmpty()){
            var res =Resource.Success(MediaStatus(response.data!!.favorite,response.data!!.id,Rated(0),false))
            movieState.postValue(res)}
        }
    }

    fun getTvShowState(tvshowID: Int, sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        movieState.postValue(Resource.Loading())
        try {
            var response = getTvShowStateUseCase.execute(tvshowID, sessionId)

            tvshowState.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
            movieState.postValue(e.message?.let { Resource.Error(it) })
        }
    }

    fun toggleFavorite(body: FavoriteReuqestBody, accountId: String, sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        toggleFavResponse.postValue(Resource.Loading())
        try {
            var response = toggleFavoriteUseCase.execute(body, accountId, sessionId)
            toggleFavResponse.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
            toggleFavResponse.postValue(e.message?.let { Resource.Error(it) })
        }
    }
    fun rateMovie(value:Number,movieId:Int,sessionId:String)= viewModelScope.launch(Dispatchers.IO) {
        movieRate.postValue(Resource.Loading())
        try {
            var response = rateMovieUseCase.execute(value,movieId,sessionId)
            movieRate.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
            movieRate.postValue(e.message?.let { Resource.Error(it) })
        }
    }
    fun rateTvShow(value:Number,tvShowId:Int,sessionId:String)= viewModelScope.launch(Dispatchers.IO) {
        tvShowRate.postValue(Resource.Loading())
        try {
            var response = rateTvShowUseCase.execute(value,tvShowId,sessionId)

            tvShowRate.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
            tvShowRate.postValue(e.message?.let { Resource.Error(it) })
        }
    }
    }