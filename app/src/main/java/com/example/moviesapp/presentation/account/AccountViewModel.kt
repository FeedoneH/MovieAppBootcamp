package com.example.moviesapp.presentation.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.account.Account
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.movie.MovieList
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(val getAccountUseCase: GetAccountUseCase,
                       val deleteSessionUseCase: DeleteSessionUseCase,
                       val getMovieFavoriteListUseCase: GetMovieFavoriteListUseCase,
                       val getTvShowFavoriteListUseCase: GetTvShowFavoriteListUseCase) : ViewModel() {
    var accountInfo: MutableLiveData<Resource<Account>> = MutableLiveData()
    var sessionStatus: MutableLiveData<Resource<SignOut>> = MutableLiveData()
    var favoriteMovies: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    var favoriteTvShow: MutableLiveData<Resource<TvShowList>> = MutableLiveData()

    fun getMoviesList(accountId: String, sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        favoriteMovies.postValue(Resource.Loading())
        try {
            val response = getMovieFavoriteListUseCase.execute(accountId, sessionId)
            favoriteMovies.postValue(response)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getFavoritesList(accountId: String, sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        favoriteTvShow.postValue(Resource.Loading())
        try {
            val response = getTvShowFavoriteListUseCase.execute(accountId, sessionId)
            favoriteTvShow.postValue(response)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getAccountInfo(sessionId: String) = viewModelScope.launch(Dispatchers.IO) {
        accountInfo.postValue(Resource.Loading())
        try {
            var response = getAccountUseCase.execute(sessionId)
            accountInfo.postValue(response)
        } catch (e: Exception) {
            Log.i("accountscreenerror", "getAuthInfo: ${e.message}")
        }
    }


    fun signOut(sessionId: String) = viewModelScope.launch(Dispatchers.IO) {

        sessionStatus.postValue(Resource.Loading())
        try {
            var response = deleteSessionUseCase.execute(sessionId)
            sessionStatus.postValue(response)
        } catch (e: Exception) {
            Log.i("accountscreenerror", "getAuthInfo: ${e.message}")
        }
    }
}