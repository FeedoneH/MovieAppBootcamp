package com.example.moviesapp.data.repository.tvshow

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
        return convertDetailResponseToResource(
                tvShowRemoteDataSource.getTvShowDetail(id)
        )
    }

    override suspend fun getTvShowCredits(id: String): Resource<Credits> {
        var response = tvShowRemoteDataSource.getTvShowCredits(id)
        if(response.isSuccessful){
            return Resource.Success(response.body()!!)
        }
        return Resource.Error(response.message())
    }

    fun convertDetailResponseToResource(response: Response<TvShowDetail>): Resource<TvShowDetail> {
        if (response.isSuccessful) {

            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }



    fun convertToResource(response: Response<TvShowList>): Resource<TvShowList> {
        if (response.isSuccessful) {

            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}