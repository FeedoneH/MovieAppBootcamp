package com.example.moviesapp.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


interface FireBaseDao {
suspend fun getCurrentUser():FirebaseUser
suspend fun taskDao():Task<Any>
}