package com.np.retrofit.practices.practical_1.api_provider

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("posts/{id}")
    fun getPostsByCall(@Path(value = "id") postId: Int): Call<Any>

    @Headers("user-key: 9900a9720d31dfd5fdb4352700c")
    @GET("posts/{id}")
    suspend fun getPostsByResponse(@Path("id") postId: Int): Response<Any>
}