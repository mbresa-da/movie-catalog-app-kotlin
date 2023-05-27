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
    private val nowPlayingList = ArrayList<MovieResults>()
    private val popularList = ArrayList<MovieResults>()
    private val topRatedList = ArrayList<MovieResults>()
    private val upcomingList = ArrayList<MovieResults>()
    val nowPlayingMovieList = MutableLiveData<List<MovieResults>>()
    val popularMovieList = MutableLiveData<List<MovieResults>>()
    val topRatedMovieList = MutableLiveData<List<MovieResults>>()
    val upcomingMovieList = MutableLiveData<List<MovieResults>>()

    fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            nowPlayingList.addAll(moviesRepository.getNowPlaying(page).results)
            nowPlayingMovieList.value = nowPlayingList
        }
    }

    fun getPopular(page: Int) {
        viewModelScope.launch {
            popularList.addAll(moviesRepository.getPopular(page).results)
            popularMovieList.value = popularList
        }
    }

    fun getTopRated(page: Int) {
        viewModelScope.launch {
            topRatedList.addAll(moviesRepository.getTopRated(page).results)
            topRatedMovieList.value = topRatedList
        }
    }

    fun getUpcoming(page: Int) {
        viewModelScope.launch {
            upcomingList.addAll(moviesRepository.getUpcoming(page).results)
            upcomingMovieList.value = upcomingList
        }
    }

}