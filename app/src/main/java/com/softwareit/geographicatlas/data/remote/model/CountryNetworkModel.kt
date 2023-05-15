package com.softwareit.geographicatlas.data.remote.model

import com.google.gson.annotations.SerializedName

data class CountryNetworkModel(

    @SerializedName("area")
    val area: Double,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("cca2")
    val cca2: String,
    @SerializedName("continents")
    val continents: List<String>,
    @SerializedName("currencies")
    val currencies: HashMap<String, Currency>?,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerializedName("name")
    val name: Name,
    @SerializedName("population")
    val population: Long,
    @SerializedName("region")
    val region: String,
    @SerializedName("maps")
    val maps: Maps,
)

