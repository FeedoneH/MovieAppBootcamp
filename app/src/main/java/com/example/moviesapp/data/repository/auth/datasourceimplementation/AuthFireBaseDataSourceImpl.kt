package com.example.moviesapp.data.repository.auth.datasourceimplementation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.repository.auth.datasource.AuthFireBaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.example.moviesapp.data.model.user.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseAppLifecycleListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNNECESSARY_SAFE_CALL")
class AuthFireBaseDataSourceImpl(val firebaseAuth: FirebaseAuth) : AuthFireBaseDataSource {
    override suspend fun logInUser(email: String, password: String) {
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
        }
    }


    override suspend fun signUpUser(userName: String, email: String, password: String): Task<AuthResult> {
        if (firebaseAuth.currentUser == null) {
            return firebaseAuth.createUserWithEmailAndPassword(email, password)
//            Log.i("fbauth", "signUpUser: ${user.result?.user?.uid}")
        }
        return firebaseAuth.pendingAuthResult
    }

    override suspend fun getCurrentUser(): FirebaseUser {
        return FirebaseAuth.getInstance().currentUser

    }

    override suspend fun signOutUser() {
        firebaseAuth.signOut()
    }
}