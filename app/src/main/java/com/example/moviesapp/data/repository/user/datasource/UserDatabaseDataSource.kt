package com.example.moviesapp.data.repository.user.datasource

import com.example.moviesapp.data.db.UserDao
import com.example.moviesapp.data.model.user.User

interface UserDatabaseDataSource {
suspend fun getUser(uid:String):User
suspend fun addUser(user: User)
}