package com.example.appmovie

import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.ItemFilmBinding

class FilmItem(
    private val binding: ItemFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(film: Film) {
        with(binding) {
            tvHeader.text = film.header
            tvGenre.text = film.genre
            tvTime.text = film.time
        }
    }
}
