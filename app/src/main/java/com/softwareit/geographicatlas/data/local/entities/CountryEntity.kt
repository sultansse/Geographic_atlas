package com.softwareit.geographicatlas.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softwareit.geographicatlas.data.model.CountriesList
import com.softwareit.geographicatlas.utils.Constants

@Entity(tableName = Constants.COUNTRIES_TABLE)
class CountryEntity(
    var countriesList: CountriesList
) {
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0
}



