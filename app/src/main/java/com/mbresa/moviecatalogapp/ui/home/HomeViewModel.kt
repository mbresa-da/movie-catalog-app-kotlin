package com.mbresa.moviecatalogapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.mbresa.moviecatalogapp.domain.repos.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    var nowPlayingPage = 1
    var popularPage = 1
    var topRatedPage = 1
    var upcomingPage = 1
    private val moviesRepository = MoviesRepository()
    val nowPlayingMovieList = MutableLiveData<List<MovieResults>>()
    val popularMovieList = MutableLiveData<List<MovieResults>>()
    val topRatedMovieList = MutableLiveData<List<MovieResults>>()
    val upcomingMovieList = MutableLiveData<List<MovieResults>>()
    val searchMovieList = MutableLiveData<List<MovieResults>>()

    fun getMovieList(path:String, page: Int) {
        viewModelScope.launch {
            when(path) {
                "now_playing" -> nowPlayingMovieList.value = moviesRepository.getList(path, page).results
                "upcoming" -> upcomingMovieList.value = moviesRepository.getList(path, page).results
                "popular" -> popularMovieList.value = moviesRepository.getList(path, page).results
                "top_rated" -> topRatedMovieList.value = moviesRepository.getList(path, page).results
            }
        }
    }

    fun searchList(query:String){
        viewModelScope.launch {
            searchMovieList.value = moviesRepository.getSearch(query).results
        }
    }


}