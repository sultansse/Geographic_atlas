package com.softwareit.geographicatlas.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softwareit.geographicatlas.data.model.CountriesList
import com.softwareit.geographicatlas.data.model.Country

class CountryTypeConverter {
    private var gson = Gson()

    @TypeConverter
    fun countryToString(country: CountriesList): String {
        return gson.toJson(country)
    }

    @TypeConverter
    fun countriesToString(characterList: CountriesList): String {
        return gson.toJson(characterList)
    }

    @TypeConverter
    fun stringToCountry(data: String): Country {
        val lisType = object : TypeToken<Country>() {}.type
        return gson.fromJson(data, lisType)
    }

    @TypeConverter
    fun stringToCountriesList(data: String): CountriesList {
        val lisType = object : TypeToken<CountriesList>() {}.type
        return gson.fromJson(data, lisType)
    }

}