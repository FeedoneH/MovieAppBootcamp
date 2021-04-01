package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.GoogleMapRepository

class GetPlacesUseCase(private val repository: GoogleMapRepository) {
    suspend fun executeGetNearbyPlaces(type:String,location:String,radius:String) = repository.getNearbyPlaces(type,location,radius)
    suspend fun executeGetDetailsOfPlace(place_id:String,fields: String)= repository.getPlaceDetails(place_id,fields)
}