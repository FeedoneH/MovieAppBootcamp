package com.example.moviesapp.data.model.map


import com.google.gson.annotations.SerializedName

data class GeoLocationResponse(
        @SerializedName("html_attributions")
    val htmlAttributions: List<Any>,
        @SerializedName("results")
    val results: List<Place>,
        @SerializedName("status")
    val status: String
)