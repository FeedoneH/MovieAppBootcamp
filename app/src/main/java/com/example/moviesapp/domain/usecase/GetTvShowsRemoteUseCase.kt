package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.repository.TvShowRepository

class GetTvShowsRemoteUseCase(val repository: TvShowRepository) {
    suspend fun executeGetPopularTvShow() = repository.getPopularTvShow()
    suspend fun executeGetTopRatedTvShow() = repository.getTopRatedTvShow()
    suspend fun executeGetTvShowDetail(id: String) = repository.getTvShowDetail(id)
    suspend fun executeGetTvShowCredits(id: String) = repository.getTvShowCredits(id)
}