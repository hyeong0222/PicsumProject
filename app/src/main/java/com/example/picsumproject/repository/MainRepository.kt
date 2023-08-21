package com.example.picsumproject.repository

import com.example.picsumproject.model.Image
import com.example.picsumproject.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPhotoList(page: Int? = null, limit: Int? = null): Flow<List<Image>> {
        return flow { emit(apiService.getPhotoList(page = page, limit = limit)) }
    }
}