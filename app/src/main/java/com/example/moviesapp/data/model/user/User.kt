package com.example.moviesapp.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class User(
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        var id: String,
        @ColumnInfo(name = "user_name")
        var userName: String?=null,
        @ColumnInfo(name = "email")
        var email: String?,

)