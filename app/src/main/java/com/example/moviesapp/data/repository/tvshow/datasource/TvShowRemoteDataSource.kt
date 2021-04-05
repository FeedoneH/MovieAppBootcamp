package com.example.moviesapp.data.repository.tvshow.datasource

import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getPopularTvShows():Response<TvShowList>
    suspend fun getTopRatedTvShows():Response<TvShowList>
    suspend fun getTvShowDetail(id:String):Response<TvShowDetail>
    suspend fun getTvShowCredits(id: String):Response<Credits>
    suspend fun geTvShowState(tvShowId: Int, sessionId: String):Response<MediaStatus>
    suspend fun rateTvShow(value: Number, tvShowId: Int, sessionId: String): Response<PostResponse>
}