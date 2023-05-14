package com.softwareit.geographicatlas.ui.countriesList

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareit.geographicatlas.data.local.entities.CountryEntity
import com.softwareit.geographicatlas.data.repository.Repository
import com.softwareit.geographicatlas.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/*

class CountriesListViewModel(
    application: Application,
//    private val countryId: Long,
) : AndroidViewModel(application) {

    private val countryDao = CountryDatabase.getInstance(application).countryDao()

    private val _countries = MediatorLiveData<List<CountryEntity>>()
    val countries: LiveData<List<Country>>
        get() = _countries.map {
            it.map { country ->
                Country(
                    country.id,
                    country.flag,
                    country.country_name,
                    country.capital_name,
                    country.population,
                    country.area,
                    country.currencies,
                    country.capital_coordinates,
                    country.region,
                )
            }
        }

    init {
//        deleteAllCountries()
//        insertMockData()
        val source = countryDao.getAllCountries()
        _countries.addSource(source) { countriesList ->
            _countries.postValue(countriesList)
        }
    }

    private fun insertMockData() {
        countryDao.insertAllCountries(MyMockData.countries)
    }

    private fun deleteAllCountries() {
        countryDao.deleteAllCountries()
    }


}
*/

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    //   ROOM
//    val getCountry : LiveData<CountryEntity> = repository.local.getCountry()
    val getAllCountries: LiveData<List<CountryEntity>> = repository.local.getAllCountries()

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

    //    RETROFIT

    var countriesResponse: MutableLiveData<Resource<CountriesList>> = MutableLiveData()

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            getCountriesSafeCall()
        }
    }


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


    private fun offlineCacheCountries(countriesList: CountriesList) {
        val countryEntity = CountryEntity(countriesList)
        insertCountry(countryEntity)
    }


    private fun handleGetCharachtersResponse(response: Response<CountriesList>): Resource<CountriesList>? {
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
    }

    /*     var searchedCharactersResponse: MutableLiveData<NetworkResult<CharacterList>> = MutableLiveData()
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