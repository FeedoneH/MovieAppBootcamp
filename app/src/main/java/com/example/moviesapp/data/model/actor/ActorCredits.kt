package com.example.moviesapp.data.model.actor


import com.google.gson.annotations.SerializedName

data class ActorCredits(
        @SerializedName("cast")
    val cast: List<Filmography>,
        @SerializedName("crew")
    val crew: List<Any>,
        @SerializedName("id")
    val id: Int
)