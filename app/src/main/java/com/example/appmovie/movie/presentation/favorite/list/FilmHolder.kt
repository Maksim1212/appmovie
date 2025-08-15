package com.example.appmovie.movie.presentation.favorite.list

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.presentation.favorite.FavoriteUiState

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(favoriteUiState: FavoriteUiState.Success.FilmFavorite) {
        with(binding) {
            tvName.text = favoriteUiState.nameRu
            tvGenre.text = favoriteUiState.genre
            tvRating.text = favoriteUiState.rating
            tvData.text = favoriteUiState.year
            tvTime.text = favoriteUiState.filmLength

            glide
                .load(favoriteUiState.cover.toUri()).into(cover)
        }
    }
}
