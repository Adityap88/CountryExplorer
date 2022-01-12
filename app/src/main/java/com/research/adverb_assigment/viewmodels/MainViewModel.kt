package com.research.adverb_assigment.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*

import com.research.adverb_assigment.data.Repository
import com.research.adverb_assigment.data.database.CountriesEntity
import com.research.adverb_assigment.models.Countries
import com.research.adverb_assigment.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    /**  ROOM DATABASE */
    val readCountries: LiveData<List<CountriesEntity>> =
        repository.local.readDatabase().asLiveData()

    private fun insertCountries(countriesEntity: CountriesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertCountries(countriesEntity)

        }
    fun deleteCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteCountries()
        }

    }


    /** RETROFIT        */

    val countriesreponse: MutableLiveData<NetworkResult<Countries>> = MutableLiveData()

    fun getCountries() = viewModelScope.launch {
        getCountriesSafeCall()
    }

    private suspend fun getCountriesSafeCall() {
        countriesreponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getCountries()
                countriesreponse.value = handleResponse(response)

                val countries = countriesreponse.value!!.data
                if (countries != null) {
                    offlineCacheData(countries)
                }
            } catch (e: Exception) {

            }
        } else {
            countriesreponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun offlineCacheData(countries: Countries) {
        val countriesEntity = CountriesEntity(countries)
        insertCountries(countriesEntity)
    }

    private fun handleResponse(response: Response<Countries>): NetworkResult<Countries>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Network Error")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API key Limited")
            }
//            response.body()!!.results.isNullOrEmpty() -> {
//                return NetworkResult.Error("Countries not found")
//            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
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
}