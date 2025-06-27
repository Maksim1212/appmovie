package com.example.appmovie.movie.presentation.favorite

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.data.modelhome.FilmModel

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val action: (FilmModel) -> Unit,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(film: FilmModel) {
        with(binding) {
            tvHeader.text = film.header
            tvGenre.text = film.genre
            tvTime.text = film.time
            tvRating.text = film.rating
            tvData.text = film.data.toString()

            glide
                .load(film.cover.toUri()).into(cover)

            root.setOnClickListener {
                action(film)
            }
        }
    }
}
