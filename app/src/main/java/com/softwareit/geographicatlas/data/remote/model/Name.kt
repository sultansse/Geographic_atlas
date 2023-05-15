package com.softwareit.geographicatlas.data.remote.model

import com.google.gson.annotations.SerializedName

data class Name(

    @SerializedName("common")
    val common: String,
)