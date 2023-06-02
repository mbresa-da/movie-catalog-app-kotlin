package com.mbresa.moviecatalogapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.R
import com.mbresa.moviecatalogapp.databinding.FragmentMovieBinding
import com.mbresa.moviecatalogapp.domain.models.MovieDetails
import com.mbresa.moviecatalogapp.ui.home.MovieAdapter
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding
    private val castAdapter = CastAdapter()
    private val similarAdapter = MovieAdapter()
    private lateinit var castLayoutManager: LinearLayoutManager
    private lateinit var similarLayoutManager: LinearLayoutManager
    private val viewModel: MovieViewModel by viewModels()
    private val args: MovieFragmentArgs by navArgs()

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
        viewModel.getSimilarList(args.movieId, 1)
        observeViewModel()

        with(binding) {
            castLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            castRecycler.adapter = castAdapter
            castRecycler.layoutManager = castLayoutManager

            similarLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            similarRecycler.adapter = similarAdapter
            similarRecycler.layoutManager = similarLayoutManager

            backToHome.setOnClickListener {
                it.findNavController().navigate(R.id.action_movieFragment_to_homeFragment)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            setupUi(it)
            castAdapter.casts = it.credits.cast
        }
        viewModel.similarMovieList.observe(viewLifecycleOwner) {
            similarAdapter.movies = it
        }
    }

    private fun setupUi(movie: MovieDetails) {
        with(binding) {
            Picasso.get().load(IMAGE_BASE_URL + movie.backdropPath).into(showMovieImage)
            movieNameTitle.text = movie.title
            yearRelease.text = movie.releaseDate?.take(4)
            var genres = ""
            for (g in movie.genres) {
                genres += "${g.name}, "
            }
            genres = genres.take(genres.length - 2)
            val secondLineText =
                "$genres | ${movie.runtime.toString()} m | ${movie.originalLanguage?.uppercase()}"
            secondLine.text = secondLineText
            val decimalFormat = DecimalFormat("#.#")
            showRating.text = decimalFormat.format(movie.voteAverage).toDouble().toString()
            tagline.text = movie.tagline
            movieOverview.text = movie.overview
            binding.detailLoader.visibility = View.GONE
        }

    }

}