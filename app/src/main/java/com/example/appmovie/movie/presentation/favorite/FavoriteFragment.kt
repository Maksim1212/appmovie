package com.example.appmovie.movie.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.appmovie.Film
import com.example.appmovie.databinding.FragmentFavoriteBinding
import com.example.appmovie.movie.data.repository.repository.FilmRepository

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var adapter: FilmAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FilmAdapter(
            FilmRepository.films,
            glide = Glide.with(this@FavoriteFragment),
            action = TODO()
        )

        binding.rvFilmFavorite.adapter = adapter
        binding.rvFilmFavorite.layoutManager = LinearLayoutManager(context)

    }
}
