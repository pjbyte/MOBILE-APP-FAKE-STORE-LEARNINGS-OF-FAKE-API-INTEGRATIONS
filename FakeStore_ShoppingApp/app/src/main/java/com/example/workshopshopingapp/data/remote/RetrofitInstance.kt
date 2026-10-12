package com.example.workshopshopingapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton object responsible for initializing and configuring Retrofit instance.
 *
 * Retrofit components:
 * 1. Base URL: The root endpoint URL ("https://fakestoreapi.com/"). Must end with trailing slash '/'.
 * 2. OkHttpClient: Handles HTTP connections, timeouts, and logging.
 * 3. ConverterFactory: GsonConverterFactory converts JSON payloads into Kotlin data models automatically.
 */
object RetrofitInstance {

    private const val BASE_URL = "https://fakestoreapi.com/"

    // OkHttp Logging Interceptor prints request/response bodies in Logcat for debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Lazy-initialized API Service instance created by Retrofit.
     */
    val apiService: FakeStoreApiService by lazy {
        retrofit.create(FakeStoreApiService::class.java)
    }
}
