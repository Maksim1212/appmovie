package com.example.appmovie.movie.presentation.infofilm.aboutmovie

import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.TextAboutFilmBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class AboutFilmsHolder(
    val binding: TextAboutFilmBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(aboutFilm: InfoFilmUiState.Success) {
        with(binding) {
            tvAboutFilm.text = aboutFilm.shortDescription
        }
    }
}
