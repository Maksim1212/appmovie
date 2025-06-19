@file:Suppress("DEPRECATED_JAVA_ANNOTATION")

package com.example.appmovie.movie.data.di

import com.example.appmovie.BuildConfig
import com.example.appmovie.movie.data.remote.KinopoiskApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Named
import javax.inject.Qualifier

@Module
class NetworkModule {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    annotation class InterceptorAuth()

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    annotation class Interceptorlogging()

    @Provides
    @Interceptorlogging
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideHttpClient(
        @InterceptorAuth authInterceptor: Interceptor,
        @Interceptorlogging loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        callAdapterFactory: CoroutineCallAdapterFactory,
        @Named("base_url") baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(gsonFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .baseUrl(baseUrl)
        .build()

    @Provides
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory =
        CoroutineCallAdapterFactory.Companion()

    @Provides
    @InterceptorAuth
    fun provideAuthInterceptor(
        @Named("api_key") apiKey: String
    ): Interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .header("X-API-KEY", apiKey)
            .build()
        chain.proceed(newRequest)
    }

    @Provides
    @Named("api_key")
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    fun provideKinopoiskApi(
        retrofit: Retrofit
    ): KinopoiskApi = retrofit.create(KinopoiskApi::class.java)
}
