package com.softwareit.geographicatlas.data.local.dao

//@Dao
//interface CountryDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCountry(countryEntity: CountryEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllCountries(countries: List<CountryEntity>)
//
//    @Delete
//    suspend fun deleteCountry(countryEntity: CountryEntity)
//
//    @Query("DELETE FROM COUNTRIES_TABLE")
//    suspend fun deleteAllCountries()
//
//    @Query("SELECT * FROM COUNTRIES_TABLE")
//    fun getAllCountries(): LiveData<List<CountryEntity>>
//
//    @Query("SELECT * FROM COUNTRIES_TABLE WHERE id = :countryId")
//    fun getCountry(countryId: Long): LiveData<CountryEntity>
//}