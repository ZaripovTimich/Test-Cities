package com.example.testcities.ui.listcities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcities.R
import com.example.testcities.data.repository.NetworkResult
import com.example.testcities.databinding.CitiesFragmentBinding
import com.example.testcities.ui.cityinformation.CityInfoFragment
import com.example.testcities.ui.listcities.adapter.CitiesAdapter
import com.example.testcities.ui.listcities.adapter.OnInteractionListener
import dagger.hilt.android.AndroidEntryPoint

private const val LIMIT_CITIES = 10

@AndroidEntryPoint
class CitiesFragment : Fragment(R.layout.cities_fragment) {

    private lateinit var binding: CitiesFragmentBinding
    private val viewModel by viewModels<CitiesViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter

    private var offset = 0
    private var canLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        citiesAdapter = CitiesAdapter(object : OnInteractionListener {
            override fun onClickCity(id: Int) {
                findNavController().navigate(
                    R.id.action_citiesFragment_to_cityInfoFragment,
                    bundleOf(CityInfoFragment.ARG_CITY_ID to id)
                )
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding = CitiesFragmentBinding.bind(view)
        onInitRecyclerView()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.stateCities.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        offset += LIMIT_CITIES
                        hideLoading()
                        if (offset == 0) {
                            citiesAdapter.setData(response.data?.data?.toList() ?: emptyList())
                        } else {
                            citiesAdapter.addData(response.data?.data?.toList() ?: emptyList())
                        }
                        canLoad = true
                    }
                    is NetworkResult.Error -> {
                        hideLoading()
                        canLoad = true
                        Toast.makeText(context, getText(R.string.error), Toast.LENGTH_LONG).show()
                    }
                    is NetworkResult.Loading -> {
                        showLoading()
                        canLoad = false
                    }
                }
            }
        }

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            citiesAdapter.findCity(text.toString())
        }
    }

    private fun getData() = viewModel.getCities(LIMIT_CITIES, offset)

    private fun onInitRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvCities.apply {
            layoutManager = linearLayoutManager
            adapter = citiesAdapter
            this.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                        val totalItemCount = citiesAdapter.itemCount - 1
                        val currentLastVisible = linearLayoutManager.findLastVisibleItemPosition()

                        if (canLoad && currentLastVisible == totalItemCount) getData()
                    }
                }
            )
        }
    }

    private fun showLoading() {
        binding.pbContainer.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbContainer.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }
}