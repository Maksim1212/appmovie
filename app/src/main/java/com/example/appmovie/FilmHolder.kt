package com.example.appmovie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import androidx.core.net.toUri

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
            tvRating.text = film.rating
            tvData.text = film.data.toInt().toString()

            glide
                .load(film.cover.toUri()).into(cover)
            // .override(holder.binding.imageView.width, holder.binding.imageView.height) //Set target size to automatically calculated imageview size
            // .diskCacheStrategy(DiskCacheStrategy.DATA)

            root.setOnClickListener {
                action(film)
            }
        }
    }
}
