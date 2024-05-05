package com.np.retrofit.practices.practical_8.api_provider

import retrofit2.Call
import retrofit2.http.*

interface APICalls {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    fun get(): Call<Any>
}