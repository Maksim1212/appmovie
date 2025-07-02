package com.example.appmovie.movie.data.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class InfoViewModelKey(val value: KClass<out ViewModel>)
