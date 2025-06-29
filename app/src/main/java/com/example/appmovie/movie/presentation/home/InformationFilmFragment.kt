package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentFavoriteBinding
import com.example.appmovie.databinding.FragmentInformationFilmBinding
import com.example.appmovie.movie.data.remote.RetrofitContainer
import com.example.appmovie.movie.data.repository.repository.FilmRepository

class InformationFilmFragment : Fragment() {

    private var _binding: FragmentInformationFilmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationFilmBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

