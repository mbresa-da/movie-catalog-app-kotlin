package com.mbresa.moviecatalogapp.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import com.mbresa.moviecatalogapp.domain.repos.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieViewModel  : ViewModel() {

    private val moviesDetailsRepository = MovieDetailRepository()
    val movieDetails = MutableLiveData<MovieDetails>()


    fun getMovieDetail(movie_id: Int) {
        viewModelScope.launch {
            movieDetails.value = moviesDetailsRepository.getMovieDetails(movie_id)
        }
    }

}