package com.softwareit.geographicatlas.data.model

import com.google.gson.annotations.SerializedName


data class Country(

    @SerializedName("name")
    val name: String,
    @SerializedName("alpha2Code")
    val alpha2Code: String,
    @SerializedName("alpha3Code")
    val alpha3Code: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("latlng")
    val latlng: List<Double>,
    @SerializedName("area")
    val area: Double,
    @SerializedName("currencies")
    val currencies: List<Currency>,
    @SerializedName("flag")
    val flag: String
)




