package com.example.testcities.data.datasource

import com.example.testcities.data.api.ApiService
import com.example.testcities.util.Constants.HOST
import com.example.testcities.util.Constants.KEY
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getCities(limit: Int, offset: Int) = service.getCities(KEY, HOST, limit, offset)

    suspend fun getInfoCity(id: Int) = service.getInfoCity(KEY, HOST, id)
}