package com.research.adverb_assigment.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.research.adverb_assigment.R
import com.research.adverb_assigment.adapters.CountryAdapter
import com.research.adverb_assigment.util.NetworkResult
import com.research.adverb_assigment.viewmodels.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { CountryAdapter() }
    private lateinit var mView: View
    private lateinit var recycler: ShimmerRecyclerView
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        recycler = mView.findViewById(R.id.recycler_view)
        fab = mView.findViewById(R.id.btnDelete)
        fab.setOnClickListener{view->
          mainViewModel.deleteCountries()
        }
        setUpRecyclerView()
        readDatabase()
        return mView
    }

    private fun setUpRecyclerView() {
        recycler.adapter = mAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }


    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readCountries.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    Log.d("HomeFragment", "readDatabase called")
                    mAdapter.setData(database[0].countries)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            })
        }
    }

    private fun requestApiData() {
        Log.d("HomeFragment", "requestApiData called")
        mainViewModel.getCountries()
        mainViewModel.countriesreponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readCountries.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].countries)
                }
            })
        }
    }

    private fun showShimmerEffect() {
        recycler.showShimmer()
    }

    private fun hideShimmerEffect() {
        recycler.hideShimmer()
    }


}

