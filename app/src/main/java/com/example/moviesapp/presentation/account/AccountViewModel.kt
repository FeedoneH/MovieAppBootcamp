package com.example.moviesapp.presentation.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetCurrentUserUseCase
import com.example.moviesapp.domain.usecase.GetUserDBUseCase
import com.example.moviesapp.domain.usecase.SignOutUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(val getCurrentUserUseCase: GetCurrentUserUseCase, val getUserDBUseCase: GetUserDBUseCase, val signOutUserUseCase: SignOutUserUseCase) : ViewModel() {
    var authInfo: MutableLiveData<Resource<User>> = MutableLiveData()
    var currentUser: MutableLiveData<User> = MutableLiveData()
    fun getAuthInfo() = viewModelScope.launch(Dispatchers.IO) {
        try {
            var user = getCurrentUserUseCase.executGetUsser()
            authInfo.postValue(user)
        } catch (e: Exception) {
            Log.i("accountscreenerror", "getAuthInfo: ${e.message}")
        }
    }

    fun getCurrentUser(uid: String) = viewModelScope.launch(Dispatchers.IO) {
        var userInfo = getUserDBUseCase.executeGetUserFromDB(uid)
        currentUser.postValue(userInfo)
    }
    fun signOut() = viewModelScope.launch(Dispatchers.IO)  {
        signOutUserUseCase.executeSignOut()
    }
}