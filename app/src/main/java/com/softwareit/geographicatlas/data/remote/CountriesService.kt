package com.softwareit.geographicatlas.data.remote

import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("alpha/{code}")
    suspend fun getCountry(@Path("name") code: String): CountryNetworkModel

    @GET("all")
    suspend fun getAllCountries(): List<CountryNetworkModel>
}