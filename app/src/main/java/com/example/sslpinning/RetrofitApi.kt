package com.example.sslpinning

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/coffee/hot")
    suspend fun getMessage(): Call<List<Coffee>>
}