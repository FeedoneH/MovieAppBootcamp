package com.example.moviesapp.data.repository.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.repository.auth.datasource.AuthFireBaseDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.AuthRepository

import com.example.moviesapp.data.model.user.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AuthFBRepositoryImpl(val authFireBaseDataSource: AuthFireBaseDataSource) : AuthRepository {
    override suspend fun signInUser(email: String, password: String) {
      var res=  authFireBaseDataSource.logInUser(email, password)
        Log.i("login", "signInUser: ${res}")
    }

    override suspend fun signUpUser(userName: String, email: String, password: String):Task<AuthResult> {
      return  authFireBaseDataSource.signUpUser(userName, email, password)

    }

    override suspend fun getCurrentUser(): Resource<User> {
        return convertoResource(authFireBaseDataSource.getCurrentUser())
    }

    override suspend fun signOutUser() {
        authFireBaseDataSource.signOutUser()
    }

    fun convertoResource(currentUser: FirebaseUser): Resource<User> {

        if (currentUser.email!=null) {
            return Resource.Success(User(id= currentUser.uid, email =  currentUser.email))
        } else
            return Resource.Loading()
    }
}