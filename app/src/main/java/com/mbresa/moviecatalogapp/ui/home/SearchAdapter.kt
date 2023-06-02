package com.mbresa.moviecatalogapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.R
import com.mbresa.moviecatalogapp.databinding.SearchItemBinding
import com.mbresa.moviecatalogapp.domain.models.MovieResults
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var movies: List<MovieResults> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = movies[position]
        with(holder.binding) {
            Picasso.get().load(IMAGE_BASE_URL + m.posterPath).into(showMovieImage)
            movieNameTitle.text = m.originalTitle
            val decimalFormat = DecimalFormat("#.#")
            showRating.text = decimalFormat.format(m.voteAverage).toDouble().toString()
            val movieId = bundleOf(Pair("movie_id", m.id))
            showMovieImage.setOnClickListener {
                it.findNavController().navigate(R.id.action_homeToDetail, movieId)
            }
        }

    }

    override fun getItemCount(): Int = movies.size
}