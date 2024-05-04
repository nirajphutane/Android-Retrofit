package com.np.retrofit.practices.practical_6.api_provider

import retrofit2.Response
import retrofit2.http.*

interface APICalls {

    // Headers

    // Static Headers
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    suspend fun getWithStaticHeaders(): Response<Any>

    // Dynamic Headers
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    suspend fun getWithDynamicHeaders(@Header("LoginId") loginId: String, @Header("Password") password: String): Response<Any>

    // Multiple Headers
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    suspend fun getWithHeaderMap(@HeaderMap headers: Map<String, String>): Response<Any>
}