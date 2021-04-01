package com.example.moviesapp.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetCurrentUserUseCase
import com.example.moviesapp.domain.usecase.LogInUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LogInViewModel(val logInUserUseCase: LogInUserUseCase, val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {
    var currentUser: MutableLiveData<Resource<User>> = MutableLiveData()


    fun logIn(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            logInUserUseCase.executeLogIn(email, password)
        } catch (e: Exception) {
            Log.i("exception ", "getUser: ${e.message}")
        }
    }

    fun getCurrentUser() = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)
        try {

            var user = getCurrentUserUseCase.executGetUsser()
            Log.i("getCurrentUserUseCase ", "getcurrent: ${user}")
            currentUser.postValue(user)
        } catch (e: Exception) {
            Log.i("exception ", "getcurrent:  ${e.message}")
            currentUser.postValue(Resource.Loading())
        }
    }

}