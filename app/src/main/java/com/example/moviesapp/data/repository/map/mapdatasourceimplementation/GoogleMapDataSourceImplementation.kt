package com.example.moviesapp.data.repository.map.mapdatasourceimplementation

import com.example.moviesapp.data.api.GoogleMapService
import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.place.DetailsResponse
import com.example.moviesapp.data.repository.map.mapdatasource.GoogleMapDataSource
import retrofit2.Response

class GoogleMapDataSourceImplementation(private val googleMapService: GoogleMapService) : GoogleMapDataSource {
    override suspend fun getNearbyPlaces(type: String, location: String, radius: String): Response<GeoLocationResponse> = googleMapService.getNearbyPlaces(type, location, radius)
    override suspend fun getPlaceDetails(place_id: String, fields:  String): Response<DetailsResponse> = googleMapService.getPlaceDetails(place_id,  fields)
}