package com.np.retrofit.practices.practical_4.api_provider

import retrofit2.Response
import retrofit2.http.*

interface APICalls {

    // GET Method

    /*@Headers("Accept: application/json", "Content-Type: application/json")
      @GET("endpoint")
      suspend fun get(): Response<Any>*/

    // Queries and URL manipulation

    // No Query
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    suspend fun getWithNoQuery(): Response<Any>

    // Static Query
    @GET("objects?id=3&name=Apple iPhone 12 Pro Max")
    suspend fun getWithStaticQuery(): Response<Any>

    // Dynamic Query with (encoded = false) (Default false)
    // "Tony+Stark", encoded=false will be resolved to 'https://example.com/endpoint/Tony+Stark'. (Already encoded)
    // "Tony Stark", encoded=false will be resolved to 'https://example.com/endpoint/Tony%2BStark'. (Non encoded)
    @GET("objects")
    suspend fun getWithDynamicQueryNoEncoded(
        @Query("id") id: Int,
        @Query("name") model: String
    ): Response<Any>

    // Dynamic Query with (encoded = true)
    // "Tony+Stark", encoded=true will be resolved to 'https://example.com/endpoint/Tony%2BStark'. (Already encoded)
    // "Tony Stark", encoded=true will be resolved to 'https://example.com/endpoint/Tony%2BStark'. (Non encoded)
    @GET("objects")
    suspend fun getWithDynamicQueryEncoded(
        @Query("id") id: Int,
        @Query("name", encoded = true) model: String
    ): Response<Any>

    @GET("objects")
    suspend fun getWithDynamicQueryInList(@Query("id") ids: List<Int>): Response<Any>

    // Optional Parameters
    @GET("objects")
    suspend fun getWithDynamicOptionalQueryEncoded(
        @Query("id") id: Int,
        @Query("name", encoded = true) model: String? = null
    ): Response<Any>

    @GET("objects")
    suspend fun getWithDynamicQueryInNullableList(@Query("id") ids: List<Int?>): Response<Any>

    // Multiple Parameters
    @GET("objects")
    suspend fun getWithQueryMapEncoded(@QueryMap(encoded = true) params: Map<String, String>): Response<Any>

    // Replacement Blocks
    // https://example.com/endpoint/my_path
    // Path parameters must not be null.
    @GET("objects/{id}/{name}")
    suspend fun getWithPathEncoded(
        @Path(value = "id", encoded = true) id: Int,
        @Path(value = "name", encoded = true) name: String
    ): Response<Any>

    // Dynamic URL Bypass
    // Base URL = https://example.com and get(@Url url = "endpoint") will be resolved to 'https://example.com/endpoint
    // Base URL = https://example.com and get(@Url url = "https://example.net/endpoint") will be resolved to 'https://example.net/endpoint.
    @GET
    suspend fun getDynamicURLBypass(@Url url: String): Response<Any>

    // Static URL Bypass
    // Base URL = https://example.com and @GET(value = "endpoint") will be resolved to 'https://example.com/endpoint
    // Base URL = https://example.com and @GET(value = "https://example.net/endpoint") will be resolved to 'https://example.net/endpoint.
    @GET(value = "https://example.com/object/4")
    suspend fun getStaticURLBypass(): Response<Any>
}