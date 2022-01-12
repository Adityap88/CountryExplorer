package com.research.adverb_assigment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [CountriesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(CountriesTypeConverter::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}