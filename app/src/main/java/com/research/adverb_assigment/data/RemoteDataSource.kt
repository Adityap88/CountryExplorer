package com.research.adverb_assigment.data

import com.research.adverb_assigment.data.network.CountriesAPI
import com.research.adverb_assigment.models.Countries
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val countriesAPI: CountriesAPI
) {

    suspend fun getCountries(): Response<Countries> {
        val ans = countriesAPI.getCountries()
        return ans

    }
}