package com.example.picsumproject.repository

import com.example.picsumproject.model.PicsumImage
import com.example.picsumproject.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getImageList(page: Int? = null, limit: Int? = null): Flow<List<PicsumImage>> {
        return flow { emit(apiService.getImageList(page = page, limit = limit)) }
    }

    suspend fun getImageDetail(id: String): Flow<PicsumImage> {
        return flow { emit(apiService.getImageDetail(id = id)) }
    }
}