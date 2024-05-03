package com.np.retrofit.practices.practical_3.api_provider

import com.np.retrofit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.restful-api.dev/some/fack/endpoints/"

    private val okHttpClient = OkHttpClient.Builder()

    init {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClient.addInterceptor(loggingInterceptor)
        }
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}