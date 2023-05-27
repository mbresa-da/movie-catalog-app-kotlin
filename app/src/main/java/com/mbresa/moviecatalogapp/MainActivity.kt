package com.mbresa.moviecatalogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mbresa.moviecatalogapp.databinding.FragmentHomeBinding
import com.mbresa.moviecatalogapp.databinding.HomeMovieItemBinding

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}