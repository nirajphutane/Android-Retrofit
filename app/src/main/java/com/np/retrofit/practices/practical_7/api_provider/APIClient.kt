package com.np.retrofit.practices.practical_7.api_provider

object ApiClient {
    val apiClient: APICalls = RetrofitClient.retrofit.create(APICalls::class.java)
}