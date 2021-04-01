package com.example.moviesapp.data.repository.auth.datasource

import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.util.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

interface AuthFireBaseDataSource {
    suspend fun logInUser(email: String, password: String)
    suspend fun signUpUser(userName: String, email: String, password: String):Task<AuthResult>
    suspend fun getCurrentUser(): FirebaseUser
    suspend fun signOutUser()
}