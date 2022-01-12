package com.research.adverb_assigment.data.database

import androidx.room.*
import com.research.adverb_assigment.data.database.CountriesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countriesEntity: CountriesEntity)

    @Query("Select * from countries_table Order by id asc")
    fun readCountries(): Flow<List<CountriesEntity>>

    @Query("Delete from countries_table")
    fun deleteCountries()


}