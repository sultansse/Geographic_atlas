package com.softwareit.geographicatlas.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softwareit.geographicatlas.data.local.entities.CountryEntity

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryEntity: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<CountryEntity>)

    @Delete
    suspend fun deleteCountry(countryEntity: CountryEntity)

    @Query("DELETE FROM COUNTRIES_TABLE")
    suspend fun deleteAllCountries()

    @Query("SELECT * FROM COUNTRIES_TABLE")
    fun getAllCountries(): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM COUNTRIES_TABLE WHERE id = :countryId")
    fun getCountry(countryId: Long): LiveData<CountryEntity>
}