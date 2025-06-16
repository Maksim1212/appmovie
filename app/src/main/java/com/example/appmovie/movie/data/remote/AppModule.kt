package com.example.appmovie.movie.data.remote

import android.app.Application
import android.content.Context
import androidx.benchmark.json.BenchmarkData
import com.bumptech.glide.signature.AndroidResourceSignature
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContex(app: Application): Context = app.applicationContext

    @Provides
    fun provideResourseProvider(context:Context) = AndroidResourceProvider(context)

    @Provides
    fun provideFusedLocationClient(context: Context) = LocationServices.getFusedLocationProvider
}
