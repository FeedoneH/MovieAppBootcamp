package com.example.moviesapp.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.util.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    suspend fun signInUser(email: String, password: String)
    suspend fun signUpUser(userName: String, email: String, password: String): Task<AuthResult>
    suspend fun getCurrentUser(): Resource<User>
    suspend fun signOutUser()
}