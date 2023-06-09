package com.mbresa.moviecatalogapp.data

import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import com.mbresa.moviecatalogapp.domain.models.MovieLists
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{list}")
    suspend fun getMovieList(
        @Path("list") path: String,
        @Query("page") page: Int,
        @Query("api_key") apikey: String
    ): MovieLists

    @GET("search/movie")
    suspend fun getSearchList(
        @Query("api_key") apikey : String,
        @Query("query") query: String
    ): MovieLists

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int,
        @Query("api_key") apikey: String
    ): MovieLists


    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apikey: String,
        @Query("append_to_response") append: String
    ): MovieDetails

}