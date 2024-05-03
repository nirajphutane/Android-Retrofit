package com.np.retrofit.practices.practical_1.api_provider

object ApiClient {
    val apiClient: ApiServices = RetrofitClient.retrofit.create(ApiServices::class.java)
}