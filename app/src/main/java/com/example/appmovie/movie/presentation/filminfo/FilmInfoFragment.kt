package com.example.appmovie.movie.presentation.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appmovie.databinding.FragmentInformationFilmBinding
import com.example.appmovie.movie.presentation.MainActivity

class FilmInfoFragment : Fragment() {

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
        arguments?.getInt("id")
        val bottomNavigationView = (activity as? MainActivity)?.binding?.bottomNavigation
        bottomNavigationView?.visibility = View.GONE
    }
}
