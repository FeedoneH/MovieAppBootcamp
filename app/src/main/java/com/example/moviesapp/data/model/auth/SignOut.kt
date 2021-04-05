package com.example.moviesapp.data.model.auth

import com.google.gson.annotations.SerializedName

data class SignOut(
        @SerializedName("success")
        val success: Boolean,
)