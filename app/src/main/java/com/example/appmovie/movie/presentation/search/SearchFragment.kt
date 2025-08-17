package com.example.appmovie.movie.presentation.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentSearchBinding
import com.example.appmovie.movie.App
import com.example.appmovie.movie.presentation.hide
import com.example.appmovie.movie.presentation.search.films.SearchFilmAdapter
import com.example.appmovie.movie.presentation.search.films.SearchFilmDecoration
import com.example.appmovie.movie.presentation.show
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var searchFilmAdapter: SearchFilmAdapter? = null

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val searchViewModel: SearchFilmViewModel by lazy {
        ViewModelProvider(this, factory).get(SearchFilmViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewForSearchFilms()

        addDecorators()

        observeUiState()

        setupSearchInput()
    }

    private fun recyclerViewForSearchFilms() {
        val recyclerView = binding.rvSearch

        searchFilmAdapter = SearchFilmAdapter(
            glide = Glide.with(this@SearchFragment)
        )

        recyclerView.adapter = searchFilmAdapter

        val layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
        recyclerView.layoutManager = layoutManager
    }

    private fun addDecorators() {
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.item_bottom_margin_favorite)
        val leftOffset = resources.getDimensionPixelSize(R.dimen.item_bottom_margin_favorite)

        val itemDecorator =
            SearchFilmDecoration(bottomOffset, leftOffset)
        binding.rvSearch.addItemDecoration(itemDecorator)
    }

    private fun setupSearchInput() {
        val searchEditText = binding.root.findViewById<EditText>(R.id.search_edit_text)

        var searchJob: Job? = null

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    val query = text?.toString()?.trim()
                    if (!query.isNullOrEmpty()) {
                        searchViewModel.searchByTitle(query)
                    }
                }
            }
        })
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiState.collect { search ->
                    when (search) {
                        SearchUiState.Error -> {
                            showError()
                            hideLoading()
                            hideSuccess()
                        }

                        SearchUiState.Loading -> {
                            showLoading()
                            hideSuccess()
                            hideError()
                        }

                        is SearchUiState.Success -> {
                            showSuccess()
                            hideLoading()
                            hideError()

                            searchFilmAdapter?.submitList(search.searchFilms)
                        }
                    }
                }
            }
        }
    }

    fun showError() {
        binding.errorImageViewSearch.show()
        binding.errorTextHeaderSearch.show()
        binding.errorTextTextSearch.show()
    }

    fun hideError() {
        binding.errorImageViewSearch.hide()
        binding.errorTextHeaderSearch.hide()
        binding.errorTextTextSearch.hide()
    }

    fun showLoading() {
        binding.progressBar.show()
    }

    fun hideLoading() {
        binding.progressBar.hide()
    }

    fun hideSuccess() {
        binding.rvSearch.hide()
    }

    fun showSuccess() {
        binding.rvSearch.show()
    }
}
