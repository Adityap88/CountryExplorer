package com.research.adverb_assigment.models


import android.os.Parcelable
import androidx.room.RawQuery
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CountriesItem(


    @SerializedName("borders")
    val borders: List<String>,
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("flag")
    val flag: String,

    @SerializedName("flags")
    val flags: @RawValue Flags,

    @SerializedName("languages")
    val languages: @RawValue Languages,
    @SerializedName("name")
    val name: @RawValue Name,
    @SerializedName("population")
    val population: Int,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subregion: String,

    ) : Parcelable