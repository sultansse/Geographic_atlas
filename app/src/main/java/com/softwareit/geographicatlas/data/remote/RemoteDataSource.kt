package com.softwareit.geographicatlas.data.remote

import com.softwareit.geographicatlas.data.model.Country
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val countryService: CountriesService
) {

    suspend fun getAllCountries(): List<Country> {
        return countryService.getAllCountries()
    }

    suspend fun getCountry(countryName: String): Country {
        return countryService.getCountry(countryName)
    }
}