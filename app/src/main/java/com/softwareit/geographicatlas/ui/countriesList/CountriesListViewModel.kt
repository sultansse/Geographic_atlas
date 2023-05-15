package com.softwareit.geographicatlas.ui.countriesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.model.Country
import com.softwareit.geographicatlas.data.repository.Repository
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

//    var countriesResponse: MutableLiveData<Resource<Country>> = MutableLiveData()

    val remoteAllCountries: MutableLiveData<List<Country>> = MutableLiveData()

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
//            getCountriesSafeCall()
                remoteAllCountries.postValue(repository.remote.getAllCountries())
//            withContext(Dispatchers.IO) {}
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