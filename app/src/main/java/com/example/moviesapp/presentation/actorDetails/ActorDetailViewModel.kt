package com.example.moviesapp.presentation.actorDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.actor.ActorCredits
import com.example.moviesapp.data.model.actor.ActorDetail
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.usecase.GetActorsRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorDetailViewModel(val getActorsRemoteUseCase: GetActorsRemoteUseCase):ViewModel() {
    var actorDetail:MutableLiveData<Resource<ActorDetail>> = MutableLiveData()
    var actorCredits:MutableLiveData<Resource<ActorCredits>> = MutableLiveData()

    fun getActorDetail(id:String) = viewModelScope.launch(Dispatchers.IO) {
        actorDetail.postValue(Resource.Loading())
        val response = getActorsRemoteUseCase.executeGetActorDetail(id)
        actorDetail.postValue(response)
    }
    fun getActorCredits(id:String) = viewModelScope.launch(Dispatchers.IO) {
        actorCredits.postValue(Resource.Loading())
        val response = getActorsRemoteUseCase.executeGetActorCredits(id)
        actorCredits.postValue(response)
    }
}
