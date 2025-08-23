package com.example.appmovie.movie.presentation.search.films

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.presentation.search.SearchUiState

class SearchFilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(searchUiState: SearchUiState.Success.FilmSearch) {
        with(binding) {
            tvName.text = searchUiState.nameRu
            tvGenre.text = searchUiState.genre
            tvRating.text = searchUiState.rating
            tvData.text = searchUiState.year
            tvTime.text = searchUiState.filmLength

            glide
                .load(searchUiState.cover.toUri()).into(cover)
        }
    }
}
