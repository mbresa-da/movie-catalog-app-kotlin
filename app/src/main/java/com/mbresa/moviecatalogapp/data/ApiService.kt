package com.mbresa.moviecatalogapp.data

import com.mbresa.moviecatalogapp.domain.models.MovieLists
import com.mbresa.moviecatalogapp.domain.models.MovieListsWithDates
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int, @Query("api_key") apikey : String ): MovieListsWithDates

    @GET("popular")
    suspend fun getPopular(@Query("page") page: Int, @Query("api_key") apikey : String ): MovieLists

    @GET("top_rated")
    suspend fun getTopRated(@Query("page") page: Int, @Query("api_key") apikey : String ): MovieLists

    @GET("upcoming")
    suspend fun getUpcoming(@Query("page") page: Int, @Query("api_key") apikey : String ): MovieListsWithDates

}