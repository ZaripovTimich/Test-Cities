package com.example.testcities.data.api

import com.example.testcities.data.models.CitiesResponse
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.util.Constants.GET_CITIES_API
import com.example.testcities.util.Constants.GET_INFO_CITY_BY_ID
import com.example.testcities.util.Constants.ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GET_CITIES_API)
    suspend fun getCities(
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") apiHost: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CitiesResponse>

    @GET(GET_INFO_CITY_BY_ID)
    suspend fun getInfoCity(
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") apiHost: String,
        @Path(ID) id: Int
    ): Response<CityInfoResponse>
}