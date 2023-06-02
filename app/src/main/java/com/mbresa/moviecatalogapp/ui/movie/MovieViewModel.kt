package com.mbresa.moviecatalogapp.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.mbresa.moviecatalogapp.domain.repos.MovieDetailRepository
import com.mbresa.moviecatalogapp.domain.repos.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel  : ViewModel() {

    private val moviesDetailsRepository = MovieDetailRepository()
    private val moviesRepository = MoviesRepository()
    val movieDetails = MutableLiveData<MovieDetails>()
    val similarMovieList = MutableLiveData<List<MovieResults>>()


    fun getMovieDetail(movie_id: Int) {
        viewModelScope.launch {
            movieDetails.value = moviesDetailsRepository.getMovieDetails(movie_id)
        }
    }

    fun getSimilarList(movieId:Int, page: Int) {
        viewModelScope.launch {
            similarMovieList.value = moviesRepository.getSimilar(movieId, page).results
        }
    }
}