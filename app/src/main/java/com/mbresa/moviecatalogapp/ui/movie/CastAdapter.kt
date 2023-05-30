package com.mbresa.moviecatalogapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbresa.moviecatalogapp.IMAGE_BASE_URL
import com.mbresa.moviecatalogapp.databinding.MovieCastItemBinding
import com.mbresa.moviecatalogapp.domain.models.Cast
import com.squareup.picasso.Picasso

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    var casts: ArrayList<Cast> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: MovieCastItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieCastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c = casts[position]
        with(holder.binding) {
            Picasso.get().load(IMAGE_BASE_URL + c.profilePath).into(castImage)
            castName.text = c.name
            castCharacter.text = c.character
        }

    }

    override fun getItemCount(): Int = casts.size
}