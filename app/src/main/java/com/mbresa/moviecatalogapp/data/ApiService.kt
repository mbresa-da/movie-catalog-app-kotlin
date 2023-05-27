package com.mbresa.moviecatalogapp.data

import android.provider.Settings.Global.getString
import com.mbresa.moviecatalogapp.BuildConfig
import com.mbresa.moviecatalogapp.R
import com.mbresa.moviecatalogapp.domain.models.MovieListsWithDates
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int, @Query("api_key") apikey : String ): MovieListsWithDates



//    @GET("character")
//    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse
//
//    @GET("character/{character_id}")
//    suspend fun getCharacterById(@Path("character_id") characterId: String):
//            SeriesCharacter
//
//    @GET("character/{character_id}")
//    suspend fun getCharactersById(@Path("character_id") characterId: String):
//            List<SeriesCharacter>
//
//    @GET("episode/{episodes}")
//    suspend fun getEpisodes(@Path("episodes") episodeIds: String): List<Episode>
//
//    @GET
//    suspend fun getLocation(@Url url: String): LocationDetails


    
}