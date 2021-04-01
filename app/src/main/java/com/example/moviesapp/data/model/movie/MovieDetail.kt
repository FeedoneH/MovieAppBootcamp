package com.example.moviesapp.data.model.movie


import com.google.gson.annotations.SerializedName

data class MovieDetail(
        @SerializedName("budget")
        val budget: Int,
        @SerializedName("homepage")
        val homepage: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("imdb_id")
        val imdbId: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("revenue")
        val revenue: Int,
        @SerializedName("runtime")
        val runtime: Int,
        @SerializedName("status")
        val status: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
)