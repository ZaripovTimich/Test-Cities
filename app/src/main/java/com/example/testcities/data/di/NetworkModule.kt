package com.example.testcities.data.di

import com.example.testcities.data.api.ApiService
import com.example.testcities.data.datasource.RemoteDataSource
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
    fun provideBaseUrl() : String = "https://wft-geo-db.p.rapidapi.com/v1/geo/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource =
        RemoteDataSource(apiService)
}