package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appmovie.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.movie.data.repository.repository.FilmRepository

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var filmRepository = FilmRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.searchEditText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
            }
        }

        var popularFilmsMain = filmRepository.getTopRankedFilms()

        binding.rvPopularFilms.adapter = PopularFilmsAdapter(
            popularFilmsMain,
            glide = Glide.with(this@HomeFragment)
        )

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_medium)
        binding.rvPopularFilms.addItemDecoration(HorizontalSpacingItemDecoration(spacingInPixels))
    }
}
