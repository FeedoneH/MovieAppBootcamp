package com.example.moviesapp.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.auth.SignOut
import com.example.moviesapp.data.model.auth.TokenId
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.AuthUseCase
import com.example.moviesapp.domain.usecase.DeleteSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LogInViewModel(val authUseCase: AuthUseCase,
val deleteSessionUseCase: DeleteSessionUseCase) : ViewModel() {
    var tokenId: MutableLiveData<Resource<TokenId>> = MutableLiveData()
    var signOut: MutableLiveData<Resource<SignOut>> = MutableLiveData()


    fun logIn() = viewModelScope.launch(Dispatchers.IO) {
        try {

          var res=  authUseCase.executeAuth()
            tokenId.postValue(res)
        } catch (e: Exception) {
            Log.i("exception ", "getUser: ${e.message}")
        }
    }
    fun signOut(sessionId:String) = viewModelScope.launch(Dispatchers.IO) {
        try {

          var res= deleteSessionUseCase.execute(sessionId)
            signOut.postValue(res)
            Log.i("tokenId ", "getcurrent: ${res.data?.success}")
        } catch (e: Exception) {
            Log.i("exception ", "getUser: ${e.message}")
        }
    }



}