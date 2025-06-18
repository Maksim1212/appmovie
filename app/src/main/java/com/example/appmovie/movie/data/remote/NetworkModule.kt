package com.example.appmovie.movie.data.remote

import com.example.appmovie.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {
    private val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"

    @Provides
    @Named("logger")
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideHttpClient(
        @Named("auth") authInterceptor: Interceptor,
        @Named("logger") loggingInterceptor: Interceptor
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
        CoroutineCallAdapterFactory()

    @Provides
    @Named("auth")
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
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideKinopoiskApi(
        retrofit: Retrofit
    ): KinopoiskApi = retrofit.create(KinopoiskApi::class.java)
}
