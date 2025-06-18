package com.example.appmovie.movie

import android.app.Application
import com.example.appmovie.movie.data.di.AppComponent
import com.example.appmovie.movie.data.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
