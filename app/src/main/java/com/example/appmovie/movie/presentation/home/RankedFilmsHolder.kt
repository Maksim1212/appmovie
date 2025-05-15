package com.example.appmovie.movie.presentation.home

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemTopMainBinding
import com.example.appmovie.movie.presentation.home.HomeUiState.RankedFilmItemState

class RankedFilmsHolder(
    val binding: ItemTopMainBinding,
    private val glide: RequestManager

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(rankedFilmItemState: RankedFilmItemState) {
        with(binding) {
            textViewPopularFilms.text = rankedFilmItemState.rank.toString()

            glide
                .load(rankedFilmItemState.image.toUri())
                .into(imagePopularFilms)
        }
    }
}
