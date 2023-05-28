package com.mbresa.moviecatalogapp.domain.repos

import com.mbresa.moviecatalogapp.BuildConfig
import com.mbresa.moviecatalogapp.data.ApiService
import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailRepository {

    private val apiService: ApiService
    private val apikey: String
        get() = BuildConfig.API_KEY

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getMovieDetails(movie_id: Int): MovieDetails = apiService.getMovieDetails(movie_id, apikey, "credits")


}