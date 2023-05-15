package com.softwareit.geographicatlas.data.local.model

import com.softwareit.geographicatlas.data.remote.model.CapitalInfo
import com.softwareit.geographicatlas.data.remote.model.Currency
import com.softwareit.geographicatlas.data.remote.model.Flags
import com.softwareit.geographicatlas.data.remote.model.Maps
import com.softwareit.geographicatlas.data.remote.model.Name

data class CountryLocalModel(

    val area: Double,
    val capital: List<String>?,
    val cca2: String,
    val continents: List<String>,
    val currencies: HashMap<String, Currency>?,
    val flags: Flags,
    val capitalInfo: CapitalInfo,
    val name: Name,
    val population: Long,
    val region: String,
    val maps: Maps,
)

