package com.mbresa.moviecatalogapp.domain.repos

import com.mbresa.moviecatalogapp.BuildConfig
import com.mbresa.moviecatalogapp.data.ApiService
import com.mbresa.moviecatalogapp.domain.models.MovieLists
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepository {

    private val apiService: ApiService
    private val apikey: String
        get() = BuildConfig.API_KEY


    init {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getList(path: String, page: Int): MovieLists =
        apiService.getMovieList(path, page, apikey)

    suspend fun getSearch(query: String): MovieLists =
        apiService.getSearchList(apikey, query)

}