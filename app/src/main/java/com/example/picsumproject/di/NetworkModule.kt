package com.example.picsumproject.di

import com.example.picsumproject.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(url: String, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(gsonConverterFactory).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideBaseUrl(): String = "https://picsum.photos"

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
}