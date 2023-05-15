package com.softwareit.geographicatlas.ui.countriesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.repository.Repository
import com.softwareit.geographicatlas.ui.model.Country
import com.softwareit.geographicatlas.ui.model.RowItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


//   ROOM
    /*

        val localAllCountries: LiveData<List<CountryEntity>> = repository.local.getAllCountries()

        fun insertCountry(countryEntity: CountryEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.insertCountry(countryEntity)
            }
        }

        fun insertAllCountries(countriesList: List<CountryEntity>) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.insertAllCountries(countriesList)
            }
        }

        fun deleteAllCountries() {
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.deleteAllCountries()
            }
        }

        fun deleteCountry(countryEntity: CountryEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.deleteCountry(countryEntity)
            }
        }
    */

//    RETROFIT

    val remoteAllCountries: MutableLiveData<List<RowItem>> = MutableLiveData()

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            // take all data for group
            val countriesList = repository.remote.getAllCountries()
            // map to list of Country class
            val continentGroups = countriesList
                .flatMap { country -> country.continents.map { continent -> continent to country } }
                .groupBy({ it.first }, { it.second })
                .toSortedMap()

            // group by continent
//            val groupedCountries = countriesList.groupBy { it.continents }

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
                                    region = it.region,
                                    maps = it.maps
                                )
                            )
                        }
            }.toCollection(rowItems)

            remoteAllCountries.postValue(rowItems)
        }
    }

    /*
           private suspend fun getCountriesSafeCall() {
               countriesResponse.postValue(Resource.Loading())
               if (hasInternetConnection()) {
                   try {
                       val response = repository.remote.getAllCountries()
                       countriesResponse.postValue(handleGetCharachtersResponse(response))

                       val countriesList = response.body()
                       if (countriesList != null) {
                           offlineCacheCountries(countriesList)
                       }


                   } catch (e: Exception) {
                       countriesResponse.postValue(Resource.Error("Charachters Not Found."))
                   }
               } else {
                   countriesResponse.postValue(Resource.Error("No Internet Connection."))
               }
           }


           private fun offlineCacheCountries(countriesList: Country) {
               val countryEntity = CountryEntity(countriesList)
               insertCountry(countryEntity)
           }


           private fun handleGetCharachtersResponse(response: Response<Country>): Resource<Country>? {
               when {
                   response.message().toString().contains("timeout") -> {
                       return Resource.Error("Timeout")
                   }

                   response.code() == 402 -> {
                       return Resource.Error("API Key Limited.")
                   }

                   response.body()!!.results.isNullOrEmpty() -> {
                       return Resource.Error("Countries Not Found.")
                   }

                   response.isSuccessful -> {
                       val countries = response.body()
                       return Resource.Success(countries!!)
                   }

                   else -> {
                       return Resource.Error(response.message())
                   }
               }
           }

           private fun hasInternetConnection(): Boolean {
               val connectivityManager = getApplication<Application>().getSystemService(
                   Context.CONNECTIVITY_SERVICE
               ) as ConnectivityManager
               val activeNetwork = connectivityManager.activeNetwork ?: return false
               val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
               return when {
                   capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                   capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                   capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                   else -> false
               }
           }*/

    /*
    SEARCH logic
    var searchedCharactersResponse: MutableLiveData<NetworkResult<CharacterList>> = MutableLiveData()
    fun searchCountries(searchQuery: String) = viewModelScope.launch {
          searchCountriesSafeCall(searchQuery)
      }

      suspend fun searchCountriesSafeCall(searchQuery: String) {
          searchedCountriesResponse.postValue(Resources.Loading())
          if (hasInternetConnection()) {
              try {
                  val response = repository.remote.searchCountry(searchQuery)
                  searchedCountriesResponse.postValue(handleGetCharachtersResponse(response))
              } catch (e: Exception) {
                  searchedCountriesResponse.postValue(Resources.Error("Charachters not found."))
              }
          } else {
              searchedCountriesResponse.postValue(Resources.Error("No Internet Connection."))
          }
      }

      private fun hasInternetConnection(): Boolean {
          val connectivityManager = getApplication<Application>().getSystemService(
              Context.CONNECTIVITY_SERVICE
          ) as ConnectivityManager
          val activeNetwork = connectivityManager.activeNetwork ?: return false
          val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
          return when {
              capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
              capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
              capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
              else -> false
          }
      }*/
}