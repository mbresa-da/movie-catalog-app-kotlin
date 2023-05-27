package com.mbresa.moviecatalogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.R
import com.mbresa.moviecatalogapp.databinding.FragmentHomeBinding
import com.mbresa.moviecatalogapp.databinding.HomeMovieItemBinding
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.getNowPlaying(viewModel.nowPlayingPage)
        viewModel.getPopular(viewModel.popularPage)
        viewModel.getTopRated(viewModel.topRatedPage)
        viewModel.getUpcoming(viewModel.upcomingPage)


    }

    private fun observeViewModel() {
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m -> makePoster(m, 1) }
        }
        viewModel.popularMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m -> makePoster(m,2) }
        }
        viewModel.topRatedMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m -> makePoster(m,3) }
        }
        viewModel.upcomingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m -> makePoster(m,4) }
        }
    }

    private fun makePoster(m: MovieResults, type: Int){
        val cvb = HomeMovieItemBinding.inflate(layoutInflater, binding.root, false)
        Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(cvb.showMovieImage)
        cvb.movieNameTitle.text = m.title
        cvb.showRating.text = m.voteAverage.toString()

        cvb.showMovieImage.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_movieFragment)
        }

        when(type){
            1 -> binding.nowPlayingHolder.addView(cvb.root)
            2 -> binding.popularHolder.addView(cvb.root)
            3 -> binding.topRatedHolder.addView(cvb.root)
            4 -> binding.upcomingHolder.addView(cvb.root)
        }
    }



}