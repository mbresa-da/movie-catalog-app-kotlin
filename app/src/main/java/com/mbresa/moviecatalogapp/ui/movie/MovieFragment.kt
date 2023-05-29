package com.mbresa.moviecatalogapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.R
import com.mbresa.moviecatalogapp.databinding.FragmentMovieBinding
import com.mbresa.moviecatalogapp.databinding.HomeMovieItemBinding
import com.mbresa.moviecatalogapp.databinding.MovieCastItemBinding
import com.mbresa.moviecatalogapp.domain.models.Cast
import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import kotlin.math.nextUp

class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(args.movieId)
        observeViewModel()
        binding.backToHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_movieFragment_to_homeFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            setupUi(it)
        }
    }

    private fun setupUi(movie: MovieDetails) {
        with(binding) {
            Picasso.get().load(IMAGE_BASE_URL + movie.backdropPath).into(showMovieImage)
            movieNameTitle.text = movie.title
            yearRelease.text = movie.releaseDate?.take(4)
            var genres = ""
            for (g in movie.genres){
                genres += "${g.name}, "
            }
            genres = genres.take(genres.length - 2)
            secondLine.text = "$genres | ${movie.runtime.toString()} m | ${movie.originalLanguage?.uppercase()}"
            val decimalFormat = DecimalFormat("#.#")
            showRating.text = decimalFormat.format(movie.voteAverage).toDouble().toString()
            tagline.text = movie.tagline
            movieOverview.text= movie.overview
            var actors = ""
            movie.credits?.cast?.let {
                for(index in 0 until minOf(it.size, 5)){
                  makeCastPoster(it[index])
                }
            }
        }

    }
    private fun makeCastPoster(m: Cast) {
        val cvb = MovieCastItemBinding.inflate(layoutInflater, binding.root, false)
        Picasso.get().load(IMAGE_BASE_URL + m.profilePath).into(cvb.castImage)
        cvb.castName.text = m.name
        cvb.castCharacter.text = m.character
        binding.castHolder.addView(cvb.root)
    }

}