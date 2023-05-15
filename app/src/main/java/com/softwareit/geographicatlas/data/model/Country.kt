package com.softwareit.geographicatlas.data.model

data class CountryNetworkModel(
    val area: Double,
    val capital: List<String>,
    val cca2: String,
    val continent: List<String>,
    val currencies: Currencies,
    val flags: Flags,
    val latlng: List<Double>,
    val name: Name,
    val population: Long,
    val region: String,
)

sealed class CountryItem {
    data class CountryWrapper(val country: Country) : CountryItem()
    data class HeaderItem(val header: String) : CountryItem()
}

data class Country(
    val area: Double,
    val capital: List<String>,
    val cca2: String,
    val continent: List<String>,
    val currencies: Currencies,
    val flags: Flags,
    val latlng: List<Double>,
    val name: Name,
    val population: Long,
    val region: String,
)




