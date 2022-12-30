package com.example.testcities.data.datasource

import com.example.testcities.BuildConfig
import com.example.testcities.data.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getCities(
        limit: Int,
        offset: Int
    ) = service.getCities(BuildConfig.KEY, BuildConfig.HOST, limit, offset)

    suspend fun getInfoCity(id: Int) = service.getInfoCity(BuildConfig.KEY, BuildConfig.HOST, id)
}