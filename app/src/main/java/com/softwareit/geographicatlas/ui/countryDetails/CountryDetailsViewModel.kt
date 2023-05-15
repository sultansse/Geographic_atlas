package com.softwareit.geographicatlas.ui.countryDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.softwareit.geographicatlas.data.repository.Repository

//@HiltViewModel
class CountryDetailsViewModel /*@Inject*/ constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application)  {

}