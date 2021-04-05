package com.example.moviesapp.data.repository.tvshow

import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.PostResponse
import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.domain.repository.TvShowRepository
import retrofit2.Response

class TvShowRepositoryImpl(val tvShowRemoteDataSource: TvShowRemoteDataSource) : TvShowRepository {
    override suspend fun getPopularTvShow(): Resource<TvShowList> {
        return convertToResource(
                tvShowRemoteDataSource.getPopularTvShows()
        )
    }

    override suspend fun getTopRatedTvShow(): Resource<TvShowList> {
        return convertToResource(
                tvShowRemoteDataSource.getTopRatedTvShows()
        )
    }

    override suspend fun getTvShowDetail(id: String): Resource<TvShowDetail> {
        return convertToResource(
                tvShowRemoteDataSource.getTvShowDetail(id)
        )
    }

    override suspend fun getTvShowCredits(id: String): Resource<Credits> {
        return convertToResource(tvShowRemoteDataSource.getTvShowCredits(id))
    }

    override suspend fun rateTvShow(value: Number, tvShowId: Int, sessionId: String): Resource<PostResponse> {
        return convertToResource(tvShowRemoteDataSource.rateTvShow(value, tvShowId, sessionId))
    }

    override suspend fun getTvShowState(tvShowId: Int, sessionId: String): Resource<MediaStatus> {
        return convertToResource(tvShowRemoteDataSource.geTvShowState(tvShowId, sessionId))
    }


    private fun <T> convertToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}