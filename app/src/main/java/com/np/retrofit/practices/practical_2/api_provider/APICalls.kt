package com.np.retrofit.practices.practical_2.api_provider

import com.np.retrofit.practices.practical_2.model.APIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APICalls {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    fun getDataByCall(): Call<List<APIResponse.Phone>>

    @GET("objects")
    suspend fun getDataByResponse(): Response<List<APIResponse.Phone>>
}