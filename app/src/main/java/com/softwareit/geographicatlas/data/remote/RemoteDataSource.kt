package com.softwareit.geographicatlas.data.remote

import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val countryService: CountriesService
) {

    suspend fun getAllCountries(): List<CountryNetworkModel> {
        return countryService.getAllCountries()
    }

    suspend fun getCountryByCode(countryCode: String): List<CountryNetworkModel> {
        return countryService.getCountry(countryCode)
    }
}