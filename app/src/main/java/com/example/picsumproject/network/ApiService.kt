package com.example.picsumproject.network

import com.example.picsumproject.model.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/list")
    suspend fun getPhotoList(@Query("page") page: Int? = null, @Query("limit") limit: Int? = null): List<Image>
}