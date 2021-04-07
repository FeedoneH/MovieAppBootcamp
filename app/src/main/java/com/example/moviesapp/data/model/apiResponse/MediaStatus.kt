package com.example.moviesapp.data.model.apiResponse


import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.android.parcel.WriteWith
import okio.Options
import org.json.JSONObject
import java.lang.ProcessBuilder.Redirect.to
import java.lang.reflect.Type
import java.util.*


@Parcelize
data class MediaStatus(
        @SerializedName("favorite")
        val favorite: Boolean?=null,
        @SerializedName("id")
        val id: Int?=null,
        @SerializedName("rated")
        var rated:  @RawValue Any?=null,
        @SerializedName("watchlist")
        val watchlist: Boolean?=null
) : Parcelable