package com.softwareit.geographicatlas.data.local

import androidx.lifecycle.LiveData
import com.softwareit.geographicatlas.data.local.dao.CountryDao
import com.softwareit.geographicatlas.data.local.entities.CountryEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val countryDao: CountryDao
) {
    suspend fun insertCountry(countryEntity: CountryEntity) {
        countryDao.insertCountry(countryEntity)
    }

    suspend fun insertAllCountries(countries: List<CountryEntity>) {
        countryDao.insertAllCountries(countries)
    }

    suspend fun deleteCountry(countryEntity: CountryEntity) {
        countryDao.deleteCountry(countryEntity)
    }

    suspend fun deleteAllCountries() {
        countryDao.deleteAllCountries()
    }

    fun getAllCountries(): LiveData<List<CountryEntity>> {
        return countryDao.getAllCountries()
    }

    fun getCountry(countryId: Long): LiveData<CountryEntity> {
        return countryDao.getCountry(countryId)
    }
}