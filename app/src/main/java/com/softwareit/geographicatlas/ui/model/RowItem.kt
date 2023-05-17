package com.softwareit.geographicatlas.ui.model

sealed class RowItem {

    data class CountryWrapper(val country: Country) : RowItem()
    data class HeaderWrapper(val continent: String) : RowItem()
}





