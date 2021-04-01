package com.example.moviesapp.data.model.place


import com.google.gson.annotations.SerializedName

data class PlacesDetails(
        @SerializedName("formatted_phone_number")
        val formattedPhoneNumber: String,
        @SerializedName("geometry")
        val geometry: GeometryX,
        @SerializedName("name")
        val name: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("website")
        val website: String
)