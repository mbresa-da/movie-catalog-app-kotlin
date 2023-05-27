package com.mbresa.moviecatalogapp.ui.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.mbresa.moviecatalogapp.domain.repos.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    var currentPage = 1
    private val moviesRepository = MoviesRepository()
    private val movieList = ArrayList<MovieResults>()
    val nowPlayingMovieList = MutableLiveData<List<MovieResults>>()

    fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            movieList.addAll(moviesRepository.getNowPlaying(page).results)
            nowPlayingMovieList.value = movieList
        }
    }

}