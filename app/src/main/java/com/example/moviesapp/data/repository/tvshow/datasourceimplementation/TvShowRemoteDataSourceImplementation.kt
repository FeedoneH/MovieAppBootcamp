package com.example.moviesapp.data.repository.tvshow.datasourceimplementation

import com.example.moviesapp.data.api.TMDBService
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImplementation(val tmdbService: TMDBService) : TvShowRemoteDataSource {
    override suspend fun getPopularTvShows(): Response<TvShowList> {
        return tmdbService.getPopularTvShowsFromAPI()

    }

    override suspend fun getTopRatedTvShows(): Response<TvShowList> {
        return  tmdbService.getTopRatedTvShowsFromAPI()
    }


    override suspend fun getTvShowDetail(id:String): Response<TvShowDetail> {
        return tmdbService.getTvShowDetailFromAPI(id)
    }

    override suspend fun getTvShowCredits(id: String): Response<Credits> {
        return  tmdbService.getTvShowCreditsFromAPI(id)
    }

    override suspend fun geTvShowState(tvShowId: Int, sessionId: String): Response<MediaStatus> {
        return tmdbService.getTvShowState(tvShowId,sessionId)
    }

    override suspend fun rateTvShow(value: Number, tvShowId: Int, sessionId: String): Response<PostResponse> {
        return tmdbService.rateTvShow(value,tvShowId,sessionId)
    }
}