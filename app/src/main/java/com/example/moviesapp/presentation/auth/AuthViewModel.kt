package com.example.moviesapp.presentation.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.auth.SessionId
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.*
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.*


class AuthViewModel(val authUseCase: AuthUseCase) : ViewModel() {
    var sessionInfo: MutableLiveData<Resource<SessionId>> = MutableLiveData()

    fun getSessionId(requestToken: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            sessionInfo.postValue(Resource.Loading())
            var response = authUseCase.getSessionID(requestToken)

                sessionInfo.postValue(response)


        } catch (e: Exception) {
            sessionInfo.postValue(e.message?.let { Resource.Error(it) })
        }
    }


}