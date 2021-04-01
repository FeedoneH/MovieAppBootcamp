package com.example.moviesapp.data.repository.map.mapdatasource

import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.place.DetailsResponse
import retrofit2.Response

interface GoogleMapDataSource {
    suspend fun getNearbyPlaces(type: String, location: String, radius: String): Response<GeoLocationResponse>
    suspend fun getPlaceDetails(place_id: String,  fields: String):Response<DetailsResponse>
}