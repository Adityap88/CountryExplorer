package com.research.adverb_assigment.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.research.adverb_assigment.models.Countries
import com.research.adverb_assigment.util.Constants.Companion.COUNTRIES_TABLE

@Entity(tableName = COUNTRIES_TABLE)
class CountriesEntity(
    var countries: Countries
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}