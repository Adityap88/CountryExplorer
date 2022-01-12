package com.research.adverb_assigment.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Languages(
    @SerializedName("ara")
    val ara: String
) : Parcelable