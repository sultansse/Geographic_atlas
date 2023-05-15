package com.softwareit.geographicatlas.data.remote.model

import com.google.gson.annotations.SerializedName

data class Maps(

    @SerializedName("google-maps")
    val googleMaps: String,
    @SerializedName("openstreetmap")
    val openStreetMaps: String
)