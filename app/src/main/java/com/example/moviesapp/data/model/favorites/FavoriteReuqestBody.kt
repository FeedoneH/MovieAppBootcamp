package com.example.moviesapp.data.model.favorites

import com.google.gson.annotations.SerializedName


data class FavoriteReuqestBody(
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("media_id")
        val mediaId: Int,
        @SerializedName("favorite")
        val favorite: Boolean,
)