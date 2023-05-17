package com.softwareit.geographicatlas.ui.countryDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
import com.softwareit.geographicatlas.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val remoteCountry: MutableLiveData<List<CountryNetworkModel>> = MutableLiveData()

    fun onLoadCountryDetails(code: String) {
        viewModelScope.launch {
            getCountryDetails(code)
        }
    }

    private suspend fun getCountryDetails(countryCode: String) {
        val countryDetails = withContext(Dispatchers.IO) {
            repository.remote.getCountryByCode(countryCode)
        }
        remoteCountry.postValue(countryDetails)
    }
}
