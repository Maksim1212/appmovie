package com.example.appmovie.movie.presentation.detail

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemTopMainBinding
import com.example.appmovie.movie.data.RankedFilmModel

class PopularFilmsHolder(
    val binding: ItemTopMainBinding,
    private val glide: RequestManager

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(popularFilms: RankedFilmModel) {
        with(binding) {
            textViewPopularFilms.text = popularFilms.rank.toString()

            glide
                .load(popularFilms.cover.toUri())
                .into(imagePopularFilms)
        }
    }
}
