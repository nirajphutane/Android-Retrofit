package com.np.retrofit.practices.practical_5.api_provider

import com.np.retrofit.practices.practical_5.model.APIResponse
import retrofit2.Response
import retrofit2.http.*

interface APICalls {

    /*POST Method*/

    // Request Body

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postByBody(@Body json: String): Response<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postByMap(@Body map: Map<String, String>): Response<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postByDataModel(@Body device: APIResponse.Device): Response<Any>

    // Form Data

    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postByFieldMap(@FieldMap map: Map<String, String>): Response<Any>

    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postByFiled(@Field("id") id: String, @Field("name") model: String): Response<Any>

    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("objects")
    suspend fun postListByFiled(@Field("ids")ids: List<String>): Response<Any>
}