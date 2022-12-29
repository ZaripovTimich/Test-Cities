package com.example.testcities.data.api

import com.example.testcities.data.models.CitiesResponse
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.util.Constants.GET_CITIES_API
import com.example.testcities.util.Constants.GET_INFO_CITY_BY_ID
import com.example.testcities.util.Constants.ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(GET_CITIES_API)
    suspend fun getCities(): Response<CitiesResponse>

    @GET(GET_INFO_CITY_BY_ID)
    suspend fun getInfoCity(@Path(ID) id: Int): Response<CityInfoResponse>
}