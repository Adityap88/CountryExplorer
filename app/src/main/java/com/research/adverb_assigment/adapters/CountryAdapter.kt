package com.research.adverb_assigment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.research.adverb_assigment.databinding.CountriesRowLayoutBinding
import com.research.adverb_assigment.models.Countries
import com.research.adverb_assigment.models.CountriesItem
import com.research.adverb_assigment.util.CountriesDiffUtil

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var countries = ArrayList<CountriesItem>()

    class MyViewHolder(
        private val binding: CountriesRowLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: CountriesItem) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CountriesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = countries[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun setData(newData: Countries) {
        val countryDiffUitl = CountriesDiffUtil(countries, newData)
        val diffUtilResult = DiffUtil.calculateDiff(countryDiffUitl)
        countries = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}