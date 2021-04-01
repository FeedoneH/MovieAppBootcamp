package com.example.moviesapp.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.map.GeoLocationResponse
import com.example.moviesapp.data.model.place.DetailsResponse
import com.example.moviesapp.data.model.place.PlacesDetails
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetPlacesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapViewModel(private val getPlacesUseCase: GetPlacesUseCase) : ViewModel() {

    var nearbyCinemas: MutableLiveData<Resource<GeoLocationResponse>> = MutableLiveData()
    var placeDetail: MutableLiveData<Resource<DetailsResponse>> = MutableLiveData()

    fun getNearbyCinemas(type: String, location: String, radius: String) = viewModelScope.launch(Dispatchers.IO) {
        nearbyCinemas.postValue(Resource.Loading())
        var places = getPlacesUseCase.executeGetNearbyPlaces(type, location, radius)
        Log.i("viewmodel", "getNearbyCinemas: ${places.data}")
        nearbyCinemas.postValue(places)
    }

    fun getDetailsOfPlace(place_id: String, fields: String) = viewModelScope.launch(Dispatchers.IO) {
        placeDetail.postValue(Resource.Loading())
       var placeDetails= getPlacesUseCase.executeGetDetailsOfPlace(place_id, fields)
        placeDetail.postValue(placeDetails)
    }
}