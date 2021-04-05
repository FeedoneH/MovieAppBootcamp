package com.example.moviesapp.data.model.apiResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class MediaStatus(
        @SerializedName("favorite")
    val favorite: Boolean,
        @SerializedName("id")
    val id: Int,
//        @SerializedName("rated")
//        val rated1: Boolean?=null ,
        @SerializedName("rated")
        val rated: @RawValue Any,
        @SerializedName("watchlist")
    val watchlist: Boolean
):Parcelable