package com.softwareit.geographicatlas.data.remote.model

import com.google.gson.annotations.SerializedName

data class Maps(

    @SerializedName("googleMaps")
    val googleMaps: String,
    @SerializedName("openStreetMaps")
    val openStreetMaps: String
)