package com.example.appmovie.movie.presentation.detail

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.data.FilmModel

class MyDiffCallback : DiffUtil.ItemCallback<FilmModel>() {

    override fun areItemsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
        return oldItem == newItem
    }
}
