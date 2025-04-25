package com.example.appmovie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val action: (Film) -> Unit,
    private val glide: RequestManager

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(film: Film) {
        with(binding) {
            tvHeader.text = film.header
            tvGenre.text = film.genre
            tvTime.text = film.time

            glide
                .load(film.cover)
                .into(cover)

            root.setOnClickListener {
                action(film)
            }
        }
    }
}
