package com.np.retrofit.practices.practical_3.api_provider

import com.np.retrofit.practices.practical_3.model.APIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APICalls {

     /*URL Resolution*/

    // With "https://api.restful-api.dev/some/fack/endpoints/" this kind of Base URL
    // the End Point URL "objects" is concatenate like as
    // https://api.restful-api.dev/some/fack/endpoints/objects
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("objects")
    fun getDataByCall(): Call<List<APIResponse.Phone>>

    // With "https://api.restful-api.dev/some/fack/endpoints/" this kind of Base URL
    // the End Point URL "objects" is concatenate like as
    // https://api.restful-api.dev/objects
    @GET("/objects")
    suspend fun getDataByResponse(): Response<List<APIResponse.Phone>>
}