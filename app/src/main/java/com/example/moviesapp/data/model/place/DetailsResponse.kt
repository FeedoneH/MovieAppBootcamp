package com.example.moviesapp.data.model.place


import com.google.gson.annotations.SerializedName

data class DetailsResponse(
        @SerializedName("html_attributions")
    val htmlAttributions: List<Any>,
        @SerializedName("result")
    val result: PlacesDetails,
        @SerializedName("status")
    val status: String
)