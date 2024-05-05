package com.np.retrofit.practices.practical_10.api_provider

import android.content.Context
import com.np.retrofit.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(context: Context) {

    private val baseURL = "https://api.restful-api.dev"

    private val okHttpClient = OkHttpClient.Builder()

    init {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClient.addInterceptor(loggingInterceptor)
        }

        // Caching
        // 1. Request is valid for a certain time
        // 2. Caching can only work if it is supported by the server
        // i.e: the Cache-Control header enabled from the server,
        // then OkHttp will respect that header and cache the response for a specific time that is being sent from the server

        val cacheSize = 5L * 1024 *1024 // 5 MiB
        val cache = Cache(context.cacheDir, cacheSize)
        okHttpClient.cache(cache)
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}