package com.softwareit.geographicatlas.data.remote

import com.softwareit.geographicatlas.data.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("alpha/{code}")
    suspend fun getCountry(@Path("name") code: String): Country

    @GET("all")
    suspend fun getAllCountries(): List<Country>
}