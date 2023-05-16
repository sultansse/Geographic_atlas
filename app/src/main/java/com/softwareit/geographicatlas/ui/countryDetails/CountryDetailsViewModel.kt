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
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    fun onViewCreatedCountryCode(code: String) {
        getCountryDetails(code)
    }

    val remoteCountry: MutableLiveData<List<CountryNetworkModel>> = MutableLiveData()

    private fun getCountryDetails(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteCountry.postValue(repository.remote.getCountryByCode(countryCode))
        }
    }
}