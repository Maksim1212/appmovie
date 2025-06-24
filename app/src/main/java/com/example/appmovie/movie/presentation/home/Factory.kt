@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.appmovie.movie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class Factory @Inject constructor(
    private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModelProviders[modelClass]
            ?: viewModelProviders.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        return provider.get() as T
    }
}
