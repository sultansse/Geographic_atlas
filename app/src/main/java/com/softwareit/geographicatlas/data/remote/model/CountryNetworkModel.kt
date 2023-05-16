package com.softwareit.geographicatlas.data.remote.model

data class CountryNetworkModel(

    val area: Double,
    val capital: List<String>?,
    val cca2: String,
    val continents: List<String>,
    val currencies: HashMap<String, Currency>?,
    val flags: Flags,
    val capitalInfo: CapitalInfo,
    val name: Name,
    val population: Long,
    val subregion: String?,
    val maps: Maps,
    val timezones: List<String>,
)

