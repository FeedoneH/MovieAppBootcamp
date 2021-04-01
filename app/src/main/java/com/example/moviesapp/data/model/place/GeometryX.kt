package com.example.moviesapp.data.model.place


import com.google.gson.annotations.SerializedName

data class GeometryX(
    @SerializedName("location")
    val location: Location,
    @SerializedName("viewport")
    val viewport: Viewport
)