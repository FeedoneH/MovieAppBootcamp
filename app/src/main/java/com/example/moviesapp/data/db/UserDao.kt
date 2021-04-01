package com.example.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.data.model.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewUser(user: User)

    @Query("Select * from user_data_table WHERE user_id LIKE :uid")
    suspend fun getUser(uid: String): User

}