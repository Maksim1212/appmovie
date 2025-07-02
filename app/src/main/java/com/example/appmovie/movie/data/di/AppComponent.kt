package com.example.appmovie.movie.data.di

import android.content.Context
import com.example.appmovie.movie.presentation.home.Factory
import com.example.appmovie.movie.presentation.home.HomeFragment
import com.example.appmovie.movie.presentation.infofilm.InfoFilmFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    fun factory(): Factory

    fun inject(fragment: HomeFragment)

    fun injectInfo(fragment: InfoFilmFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}
