package com.softwareit.geographicatlas.data.model

data class AllDataClassItem(
    val area: Int,
    val capital: List<String>,
    val cca2: String,
    val continents: List<String>,
//    val currencies: Currencies,
    val flags: Flags,
    val latlng: List<Double>,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val region: String,
)