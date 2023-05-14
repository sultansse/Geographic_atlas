package com.softwareit.geographicatlas.ui.countryDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CountryDetailsViewModel(
    application: Application,
    private val countryId: Long,
) : AndroidViewModel(application)  {

}