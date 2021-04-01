package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.map.Place
import com.example.moviesapp.data.model.place.DetailsResponse
import com.example.moviesapp.data.util.Resource

interface GoogleMapRepository {
    suspend fun getNearbyPlaces(type: String, location: String, radius: String): Resource<GeoLocationResponse>
    suspend fun getPlaceDetails(place_id: String,  fields:  String): Resource<DetailsResponse>
}