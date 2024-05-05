package com.np.retrofit.practices.practical_11.api_provider

import android.content.Context
import android.net.ConnectivityManager
import com.np.retrofit.BuildConfig
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class RetrofitClient(private val context: Context) {

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

        // Caching if Cache-Control header not enabled

        okHttpClient.apply {
            cache(Cache(File(context.cacheDir, "http-cache"), 5L * 1024 * 1024)) // 5 MiB
            addNetworkInterceptor(CacheInterceptor()) // only if Cache-Control header is not enabled from the server
            addInterceptor(ForceCacheInterceptor())
        }

        // We can create a ForceCacheInterceptor in addition to the above one
        // (CacheInterceptor, only if Cache-Control header is not enabled from the server).
        // Here we are adding ForceCacheInterceptor to OkHttpClient using addInterceptor()
        // and CacheInterceptor using addNetworkInterceptor().
        //
        //addInterceptor: used to add the interceptor at the application level.
        //addNetworkInterceptor: As the name says, used to add the interceptor at the network level.
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inner class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(30, TimeUnit.SECONDS)
                .build()
            return response
                .newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .removeHeader("Pragma")
                .build()
        }
    }

    inner class ForceCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain.request().newBuilder()
            // As per Network status
            if (!hasNetworkAvailable(context)) {
                builder
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .header("Cache-Control", "public, only-if-cached, max-stale=60") // Offline cache available for 1 min
                    .removeHeader("Pragma")
                    .build()
            }
            return chain.proceed(builder.build());
        }
    }

    private fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager
        val network = manager.activeNetwork
        return (network != null)
    }
}