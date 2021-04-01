package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.user.User

interface UserRepository {
    suspend fun getUser(uid:String): User
    suspend fun addUser(user: User)
}