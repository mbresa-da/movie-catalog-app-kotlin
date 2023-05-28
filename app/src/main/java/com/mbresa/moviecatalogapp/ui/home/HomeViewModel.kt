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

    fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            nowPlayingMovieList.value = moviesRepository.getNowPlaying(page).results
        }
    }

    fun getPopular(page: Int) {
        viewModelScope.launch {
            popularMovieList.value = moviesRepository.getPopular(page).results
        }
    }

    fun getTopRated(page: Int) {
        viewModelScope.launch {
            topRatedMovieList.value = moviesRepository.getTopRated(page).results
        }
    }

    fun getUpcoming(page: Int) {
        viewModelScope.launch {
            upcomingMovieList.value = moviesRepository.getUpcoming(page).results
        }
    }

}