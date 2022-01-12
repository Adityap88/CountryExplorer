package com.research.adverb_assigment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.research.adverb_assigment.R

class Details : Fragment() {

    private val args by navArgs<DetailsArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView = inflater.inflate(R.layout.country_details, container, false)
        val img = mView.findViewById<ImageView>(R.id.imgdFlag)
        img.load(args.result.flags.png)
        val name = mView.findViewById<TextView>(R.id.txtdName)
        name.setText(args.result.name.official)
        val capital = mView.findViewById<TextView>(R.id.txtdCapital)
        capital.setText(args.result.capital.get(0))
        val region = mView.findViewById<TextView>(R.id.txtdRegion)
        region.setText(args.result.region)
        val subregion = mView.findViewById<TextView>(R.id.txtdSubregion)
        subregion.setText(args.result.subregion)
        val population = mView.findViewById<TextView>(R.id.txtdPopulation)
        population.setText(args.result.population.toString())
        val borders = mView.findViewById<TextView>(R.id.txtdBorders)
        borders.setText("")
        for (bor in args.result.borders) {
            borders.append(bor)
            borders.append("\n")
        }
        val languages = mView.findViewById<TextView>(R.id.txtdLanguages)
        languages.setText(args.result.languages.ara)



        return mView

    }
}