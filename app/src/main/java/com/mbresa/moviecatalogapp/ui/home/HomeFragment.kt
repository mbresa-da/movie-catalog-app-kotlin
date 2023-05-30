package com.mbresa.moviecatalogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbresa.moviecatalogapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val nowPlayingAdapter = MovieAdapter()
    private val popularAdapter = MovieAdapter()
    private val topRatedAdapter = MovieAdapter()
    private val upcomingAdapter = MovieAdapter()
    private val searchAdapter = SearchAdapter()
    private lateinit var nowPlayingLayoutManager: LinearLayoutManager
    private lateinit var popularLayoutManager: LinearLayoutManager
    private lateinit var topRatedLayoutManager: LinearLayoutManager
    private lateinit var upcomingLayoutManager: LinearLayoutManager
    private lateinit var searchLayoutManager: LinearLayoutManager

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

        if (viewModel.nowPlayingMovieList.value == null) {
            viewModel.getMovieList("now_playing", viewModel.nowPlayingPage)
        }
        if (viewModel.popularMovieList.value == null) {
            viewModel.getMovieList("popular", viewModel.popularPage)
        }
        if (viewModel.topRatedMovieList.value == null) {
            viewModel.getMovieList("top_rated", viewModel.topRatedPage)
        }
        if (viewModel.upcomingMovieList.value == null) {
            viewModel.getMovieList("upcoming", viewModel.upcomingPage)
        }
        with(binding) {
            nowPlayingLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            nowPlayingRecycler.adapter = nowPlayingAdapter
            nowPlayingRecycler.layoutManager = nowPlayingLayoutManager

            popularLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            popularRecycler.adapter = popularAdapter
            popularRecycler.layoutManager = popularLayoutManager

            topRatedLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            topRatedRecycler.adapter = topRatedAdapter
            topRatedRecycler.layoutManager = topRatedLayoutManager

            upcomingLayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            upcomingRecycler.adapter = popularAdapter
            upcomingRecycler.layoutManager = upcomingLayoutManager

            searchLayoutManager =
                LinearLayoutManager(activity)
            searchRecycler.adapter = searchAdapter
            searchRecycler.layoutManager = searchLayoutManager

            searchBar.doOnTextChanged { text, start, before, count ->
//                viewModel.searchCharacters(text.toString())
                if(text?.isNotEmpty() == true){
                    layoutWithList.visibility = View.GONE
                    searchRecycler.visibility = View.VISIBLE
                    viewModel.searchList(text.toString())
                }
                else{
                    layoutWithList.visibility = View.VISIBLE
                    searchRecycler.visibility = View.GONE
                }

            }
        }


    }

    private fun observeViewModel() {

        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner) {
            nowPlayingAdapter.movies = it
            binding.loader.visibility = View.GONE
        }

        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            popularAdapter.movies = it
        }

        viewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            topRatedAdapter.movies = it
        }

        viewModel.upcomingMovieList.observe(viewLifecycleOwner) {
            upcomingAdapter.movies = it
        }
        viewModel.searchMovieList.observe(viewLifecycleOwner) {
            searchAdapter.movies = it
        }

    }


}