package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.model.credits.Credits
import com.example.moviesapp.data.model.tvshow.TvShowDetail
import com.example.moviesapp.data.model.tvshow.TvShowList
import com.example.moviesapp.data.util.Resource


interface TvShowRepository {
    suspend fun getPopularTvShow(): Resource<TvShowList>
    suspend fun getTopRatedTvShow():Resource<TvShowList>
    suspend fun getTvShowDetail(id:String):Resource<TvShowDetail>
    suspend fun getTvShowCredits(id:String):Resource<Credits>
}
