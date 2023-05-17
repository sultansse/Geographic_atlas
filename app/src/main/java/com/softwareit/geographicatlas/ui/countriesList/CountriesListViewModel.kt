package com.softwareit.geographicatlas.ui.countriesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.repository.Repository
import com.softwareit.geographicatlas.ui.model.Country
import com.softwareit.geographicatlas.ui.model.RowItem
import com.softwareit.geographicatlas.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val remoteAllCountries: MutableLiveData<Resource<List<RowItem>>> = MutableLiveData()

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        remoteAllCountries.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            // take all data for group
            val countriesList = repository.remote.getAllCountries()
            // map to list of Country class
            val continentGroups = countriesList
                .flatMap { country -> country.continents.map { continent -> continent to country } }
                .groupBy({ it.first }, { it.second })
                .toSortedMap()

            val rowItems = mutableListOf<RowItem>()

            continentGroups.flatMap { (continent, countryNetworkModels) ->
                listOf(RowItem.HeaderWrapper(continent)) +
                        countryNetworkModels.map {
                            RowItem.CountryWrapper(
                                Country(
                                    area = it.area,
                                    capital = it.capital,
                                    cca2 = it.cca2,
                                    continents = it.continents,
                                    currencies = it.currencies,
                                    flags = it.flags,
                                    capitalInfo = it.capitalInfo,
                                    name = it.name,
                                    population = it.population,
                                    subregion = it.subregion,
                                    maps = it.maps,
                                    timezones = it.timezones,
                                )
                            )
                        }
            }.toCollection(rowItems)

            remoteAllCountries.postValue(Resource.Success(rowItems))
        }
    }
}