package com.example.testcities.data.models

data class CityInfo(
    val id: Int,
    val wikiDataId: String,
    val type: String,
    val city: String,
    val name: String,
    val country: String,
    val countryCode: String,
    val region: String,
    val regionCode: String,
    val elevationMeters: Int,
    val latitude: Double,
    val longitude: Double,
    val population: Int,
    val timezone: String,
    val distance: String?,
    val deleted: Boolean,
    val placeType: String
)