package com.example.testcities.data.repository

import com.example.testcities.data.models.CitiesResponse
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.data.datasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getCities(limit: Int, offset: Int): Flow<NetworkResult<CitiesResponse>> =
        flow {
            emit(safeApiCall { remoteDataSource.getCities(limit, offset) })
        }.flowOn(Dispatchers.IO)

    suspend fun getInfoCity(id: Int): Flow<NetworkResult<CityInfoResponse>> =
        flow {
            emit(safeApiCall { remoteDataSource.getInfoCity(id) })
        }.flowOn(Dispatchers.IO)
}