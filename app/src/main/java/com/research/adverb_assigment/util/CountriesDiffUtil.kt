package com.research.adverb_assigment.util

import androidx.recyclerview.widget.DiffUtil
import com.research.adverb_assigment.models.CountriesItem
import java.lang.reflect.Array

class CountriesDiffUtil(
    private val oldList: ArrayList<CountriesItem>,
    private val newList: ArrayList<CountriesItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}