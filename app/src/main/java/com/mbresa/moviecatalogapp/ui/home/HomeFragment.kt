package com.mbresa.moviecatalogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.databinding.FragmentHomeBinding
import com.mbresa.moviecatalogapp.databinding.HomeMovieItemBinding
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

        viewModel.getNowPlaying(viewModel.currentPage)

        setupNowPlaying()
        setupPopular()
        setupTopRated()
        setupUpcoming()

    }

    private fun observeViewModel() {
//        viewModel.characterLiveData.observe(viewLifecycleOwner) {
//            adapter.characters = it
//            binding.loader.visibility = View.GONE
//            binding.loadMoreloader.visibility = View.GONE
//        }
    }

    private fun setupNowPlaying(){
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m ->
                val customViewBinding = HomeMovieItemBinding.inflate(layoutInflater, binding.root, false)
                Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(customViewBinding.showMovieImage)
                customViewBinding.movieNameTitle.text = m.title
                binding.nowPlayingHolder.addView(customViewBinding.root)
            }
        }
    }

    private fun setupPopular(){
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m ->
                val customViewBinding = HomeMovieItemBinding.inflate(layoutInflater, binding.root, false)
                Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(customViewBinding.showMovieImage)
                customViewBinding.movieNameTitle.text = m.title
                binding.popularHolder.addView(customViewBinding.root)
            }
        }
    }

    private fun setupTopRated(){
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m ->
                val customViewBinding = HomeMovieItemBinding.inflate(layoutInflater, binding.root, false)
                Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(customViewBinding.showMovieImage)
                customViewBinding.movieNameTitle.text = m.title
                binding.topRatedHolder.addView(customViewBinding.root)
            }
        }
    }

    private fun setupUpcoming(){
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner){ movie ->
            movie.forEach { m ->
                val customViewBinding = HomeMovieItemBinding.inflate(layoutInflater, binding.root, false)
                Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(customViewBinding.showMovieImage)
                customViewBinding.movieNameTitle.text = m.title
                binding.upcomingHolder.addView(customViewBinding.root)
            }
        }
    }


}