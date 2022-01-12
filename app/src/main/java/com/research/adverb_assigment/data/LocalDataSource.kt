package com.research.adverb_assigment.data

import com.research.adverb_assigment.data.database.CountriesDao
import com.research.adverb_assigment.data.database.CountriesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val countriesDao: CountriesDao
) {

    fun readDatabase(): Flow<List<CountriesEntity>> {
        return countriesDao.readCountries()
    }

    suspend fun insertCountries(countriesEntity: CountriesEntity) {
        countriesDao.insertCountry(countriesEntity)
    }

    fun deleteCountries() {
        countriesDao.deleteCountries()
    }
}