package com.softwareit.geographicatlas.data.remote.model

import com.google.gson.annotations.SerializedName

data class Currency(

    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)
