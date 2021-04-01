package com.example.moviesapp.presentation.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.*
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.*


class SignUpViewModel(val signUpUserUseCase: SignUpUserUseCase,
                      val signOutUserUseCase: SignOutUserUseCase,
                      val addUserDBUseCase: AddUserDBUseCase,
                      val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {
    var newUser: MutableLiveData<Resource<User>> = MutableLiveData()


    fun signUp(userName: String, email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
         var task=   signUpUserUseCase.executeSignUp(userName, email, password)
            Tasks.await(task)
        } catch (e: Exception) {
            Log.i("exception ", "getUser: ${e.message}")
        }
    }

    fun getCurrentUser() = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)
        try {

            var user = getCurrentUserUseCase.executGetUsser()
            Log.i("getCurrentUserUseCase ", "getcurrent: ${user}")
            newUser.postValue(user)
        } catch (e: Exception) {
            Log.i("exception ", "getcurrent: ${e.message}")
        }
    }

    fun addUserToDB(user: User)= viewModelScope.launch(Dispatchers.IO) {
        addUserDBUseCase.executeAddUser(user)
    }

    fun signOut() = viewModelScope.launch(Dispatchers.IO) {
        signOutUserUseCase.executeSignOut()
    }

}