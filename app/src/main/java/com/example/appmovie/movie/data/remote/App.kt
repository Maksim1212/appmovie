package com.example.appmovie.movie.data.remote

import android.app.Application

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
