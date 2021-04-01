package com.example.moviesapp.data.repository.user

import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.repository.user.datasource.UserDatabaseDataSource
import com.example.moviesapp.domain.repository.UserRepository

class UserRepositoryImpl(val userDatabaseDataSource: UserDatabaseDataSource):UserRepository {
    override suspend fun getUser(uid:String): User {

     return userDatabaseDataSource.getUser(uid)
    }

    override suspend fun addUser(user:User) {
     userDatabaseDataSource.addUser(user)
    }
}