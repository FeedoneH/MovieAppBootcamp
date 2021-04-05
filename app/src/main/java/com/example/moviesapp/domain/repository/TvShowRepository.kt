package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.util.Resource


interface TvShowRepository {
    suspend fun getPopularTvShow(): Resource<TvShowList>
    suspend fun getTopRatedTvShow(): Resource<TvShowList>
    suspend fun getTvShowDetail(id: String): Resource<TvShowDetail>
    suspend fun getTvShowCredits(id: String): Resource<Credits>
    suspend fun rateTvShow(value: Number,tvShowId: Int, sessionId: String): Resource<PostResponse>
    suspend fun getTvShowState(tvShowId: Int, sessionId: String): Resource<MediaStatus>
}
