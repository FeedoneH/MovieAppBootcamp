package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.TvShowRepository

class GetTvShowStateUseCase(val repository: TvShowRepository) {
    suspend fun execute(tvshowId:Int,sessionId:String) = repository.getTvShowState(tvshowId,sessionId)
}