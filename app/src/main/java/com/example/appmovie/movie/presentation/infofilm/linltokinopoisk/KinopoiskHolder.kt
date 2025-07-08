package com.example.appmovie.movie.presentation.infofilm.linltokinopoisk

import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.TextWebUrlBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class KinopoiskHolder(
    val binding: TextWebUrlBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(webUrl: InfoFilmUiState.Success) {
        with(binding) {
            tvWebUrl.text = webUrl.webUrl
        }
    }
}
