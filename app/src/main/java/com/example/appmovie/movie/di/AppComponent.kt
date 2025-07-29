package com.example.appmovie.movie.di

import android.content.Context
import com.example.appmovie.movie.presentation.favorite.FavoriteFragment
import com.example.appmovie.movie.presentation.home.Factory
import com.example.appmovie.movie.presentation.home.HomeFragment
import com.example.appmovie.movie.presentation.infofilm.InfoFilmFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        AppModule::class,
        DataBaseModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun factory(): Factory

    fun inject(fragment: HomeFragment)

    fun inject(fragment: InfoFilmFragment)

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}
