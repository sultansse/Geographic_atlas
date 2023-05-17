package com.softwareit.geographicatlas.ui.countriesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
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

    private fun getAllCountries() {
        remoteAllCountries.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val countriesList = repository.remote.getAllCountries()
            val rowItems = mapToRowItems(countriesList)
            remoteAllCountries.postValue(Resource.Success(rowItems))
        }
    }

    private fun mapToRowItems(countriesList: List<CountryNetworkModel>): List<RowItem> {
        val continentGroups = countriesList
            .flatMap { country -> country.continents.map { continent -> continent to country } }
            .groupBy({ it.first }, { it.second })
            .toSortedMap()

        val rowItems = mutableListOf<RowItem>()

        continentGroups.flatMap { (continent, countryNetworkModels) ->
            val header = RowItem.HeaderWrapper(continent)
            val countryWrappers = countryNetworkModels.map { countryNetworkModel ->
                RowItem.CountryWrapper(
                    mapToCountry(countryNetworkModel)
                )
            }
            listOf(header) + countryWrappers
        }.toCollection(rowItems)

        return rowItems
    }

    private fun mapToCountry(countryNetworkModel: CountryNetworkModel): Country {
        return Country(
            area = countryNetworkModel.area,
            capital = countryNetworkModel.capital,
            cca2 = countryNetworkModel.cca2,
            continents = countryNetworkModel.continents,
            currencies = countryNetworkModel.currencies,
            flags = countryNetworkModel.flags,
            capitalInfo = countryNetworkModel.capitalInfo,
            name = countryNetworkModel.name,
            population = countryNetworkModel.population,
            subregion = countryNetworkModel.subregion,
            maps = countryNetworkModel.maps,
            timezones = countryNetworkModel.timezones
        )
    }
}
