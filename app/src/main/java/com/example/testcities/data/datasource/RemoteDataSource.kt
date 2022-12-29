package com.example.testcities.data.datasource

import com.example.testcities.data.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getCities() = service.getCities()

    suspend fun getInfoCity(id: Int) = service.getInfoCity(id)
}