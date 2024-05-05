package com.np.retrofit.practices.practical_9.api_provider

import retrofit2.Call
import retrofit2.http.*

interface APICalls {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    fun get(): Call<Any>
}