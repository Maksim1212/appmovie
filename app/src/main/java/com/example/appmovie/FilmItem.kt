package com.example.appmovie

import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.data.FilmModel

class FilmItem(
    private val binding: ItemFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(film: FilmModel) {
        with(binding) {
            tvHeader.text = film.header
            tvGenre.text = film.genre
            tvTime.text = film.time
            tvRating.text = film.rating
            tvTime.text = film.data.toString()
        }
    }
}
