package com.example.moviesapp.data.model.auth

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SessionId(
        @SerializedName("success")
        val success: Boolean,
        @SerializedName("session_id")
        val sessionId: String
):Parcelable
