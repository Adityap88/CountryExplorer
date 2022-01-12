package com.research.adverb_assigment.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.google.android.material.card.MaterialCardView
import com.research.adverb_assigment.models.CountriesItem
import com.research.adverb_assigment.ui.fragments.HomeFragmentDirections

class CountryRowBinding {

    companion object {


        @BindingAdapter("onCountryClickedListener")
        @JvmStatic
        fun onCountryClickedListener(countryRowLayout: MaterialCardView, result: CountriesItem) {
            countryRowLayout.setOnClickListener {
                try {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToDetails(result)
                    countryRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {

                }
            }
        }

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            imageView.load(url) {
                crossfade(600)
            }
        }

    }
}