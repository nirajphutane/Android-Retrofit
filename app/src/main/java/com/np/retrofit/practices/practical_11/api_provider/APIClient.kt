package com.np.retrofit.practices.practical_11.api_provider

import android.content.Context

class ApiClient(context: Context) {
    val apiClient: APICalls = RetrofitClient(context).retrofit.create(APICalls::class.java)
}