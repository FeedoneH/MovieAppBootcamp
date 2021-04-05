package com.example.moviesapp.data.model.apiResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rated(
    @SerializedName("value")
    val value: Int
):Parcelable