package com.example.picsumproject.network

import com.example.picsumproject.model.PicsumImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/list")
    suspend fun getImageList(@Query("page") page: Int? = null, @Query("limit") limit: Int? = null): List<PicsumImage>

    @GET("id/{id}/info")
    suspend fun getImageDetail(@Path("id") id: String): PicsumImage
}