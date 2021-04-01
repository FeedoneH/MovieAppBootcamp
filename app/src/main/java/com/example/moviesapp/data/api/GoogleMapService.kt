package com.example.moviesapp.data.api

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.place.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Named

@Named("googlemap")
interface GoogleMapService {
    @GET("nearbysearch/json")
    suspend fun getNearbyPlaces(@Query("type") type: String, @Query("location") location: String, @Query("radius") radius: String, @Query("key") api_key: String = BuildConfig.GOOGLE_API_KEY): Response<GeoLocationResponse>

    @GET("details/json")
    suspend fun getPlaceDetails(@Query("place_id") place_id: String, @Query("fields", encoded = true) fields: String, @Query("key") api_key: String = BuildConfig.GOOGLE_API_KEY): Response<DetailsResponse>

}