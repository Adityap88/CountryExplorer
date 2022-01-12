package com.research.adverb_assigment.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.research.adverb_assigment.models.Countries

class CountriesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun countriesToString(countries: Countries): String {
        return gson.toJson(countries)
    }

    @TypeConverter
    fun stringToCountries(data: String): Countries {
        val listType = object : TypeToken<Countries>() {}.type
        return gson.fromJson(data, listType)
    }

}