package com.example.moviesapp.data.model.apiResponse


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)