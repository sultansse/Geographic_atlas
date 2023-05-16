package com.softwareit.geographicatlas.data.remote

import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("alpha/{cca2}")
    suspend fun getCountry(@Path("cca2") cca2: String): List<CountryNetworkModel>

    @GET("all")
    suspend fun getAllCountries(): List<CountryNetworkModel>
}