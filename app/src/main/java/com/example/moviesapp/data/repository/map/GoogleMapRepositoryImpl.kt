package com.example.moviesapp.data.repository.map

import android.util.Log
import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.map.Place
import com.example.moviesapp.data.model.place.DetailsResponse
import com.example.moviesapp.data.repository.map.mapdatasource.GoogleMapDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.GoogleMapRepository

class GoogleMapRepositoryImpl(private val googleMapDataSource: GoogleMapDataSource) : GoogleMapRepository {
    override suspend fun getNearbyPlaces(type: String, location: String, radius: String): Resource<GeoLocationResponse> {
        var response = googleMapDataSource.getNearbyPlaces(type, location, radius)
        if (response.isSuccessful) {
            response.body()?.let {
                Log.i("repoimpl", "getNearbyPlaces: ${it.results}")
                return Resource.Success(it) }
        }
        return Resource.Loading()
    }

    override suspend fun getPlaceDetails(place_id: String, fields:  String): Resource<DetailsResponse> {
        var response = googleMapDataSource.getPlaceDetails(place_id,  fields)
        if (response.isSuccessful) {
            response.body()?.let { return Resource.Success(it) }
        }
        return Resource.Loading()
    }


}