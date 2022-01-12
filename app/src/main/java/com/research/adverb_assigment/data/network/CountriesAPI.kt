package com.research.adverb_assigment.data.network

import com.research.adverb_assigment.models.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {

    @GET("/v3.1/region/asia")
    suspend fun getCountries(): Response<Countries>
}