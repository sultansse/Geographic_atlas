package com.softwareit.geographicatlas.data.local

//class LocalDataSource @Inject constructor(
//    private val countryDao: CountryDao
//) {
//    suspend fun insertCountry(countryEntity: CountryEntity) {
//        countryDao.insertCountry(countryEntity)
//    }
//
//    suspend fun insertAllCountries(countries: List<CountryEntity>) {
//        countryDao.insertAllCountries(countries)
//    }
//
//    suspend fun deleteCountry(countryEntity: CountryEntity) {
//        countryDao.deleteCountry(countryEntity)
//    }
//
//    suspend fun deleteAllCountries() {
//        countryDao.deleteAllCountries()
//    }
//
//    fun getAllCountries(): LiveData<List<CountryEntity>> {
//        return countryDao.getAllCountries()
//    }
//
//    fun getCountry(countryId: Long): LiveData<CountryEntity> {
//        return countryDao.getCountry(countryId)
//    }
//}