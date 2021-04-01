package com.example.moviesapp.data.repository.user.datasourceimplementation

import com.example.moviesapp.data.db.UserDao
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.data.repository.user.datasource.UserDatabaseDataSource

class  UserDatabaseDataSourceImpl(val userDao: UserDao) :UserDatabaseDataSource{
    override suspend fun getUser(uid:String): User {
        return userDao.getUser(uid)
    }

    override suspend fun addUser(user: User) {
    userDao.addNewUser(user)
    }

}