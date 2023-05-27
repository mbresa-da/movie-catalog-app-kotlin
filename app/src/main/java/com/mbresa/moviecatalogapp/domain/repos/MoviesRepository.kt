package com.mbresa.moviecatalogapp.domain.repos

import com.mbresa.moviecatalogapp.BuildConfig
import com.mbresa.moviecatalogapp.data.ApiService
import com.mbresa.moviecatalogapp.domain.models.MovieListsWithDates
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.util.*

class MoviesRepository {

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

    suspend fun getNowPlaying(page: Int): MovieListsWithDates =
        apiService.getNowPlaying(page, apikey)


}