package com.example.testcities.ui.listcities

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcities.R
import com.example.testcities.data.repository.NetworkResult
import com.example.testcities.databinding.CitiesFragmentBinding
import com.example.testcities.ui.cityinformation.CityInfoFragment
import com.example.testcities.ui.listcities.adapter.CitiesAdapter
import com.example.testcities.ui.listcities.adapter.OnInteractionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitiesFragment : Fragment(R.layout.cities_fragment) {

    private lateinit var binding: CitiesFragmentBinding
    private val viewModel by viewModels<CitiesViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter

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
        viewModel.getCities()
        binding = CitiesFragmentBinding.bind(view)
        onInitRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateCities.collect { response ->
                    when(response) {
                        is NetworkResult.Success -> {
                            hideLoading()
                            citiesAdapter.submitList(response.data?.data?.toList())
                        }
                        is NetworkResult.Error -> {
                            hideLoading()
                            //TODO Show Error Dialog
                        }
                        is NetworkResult.Loading -> {
                            showLoading()
                        }
                    }
                }
            }
        }
    }

    private fun onInitRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCities.apply {
            layoutManager = linearLayoutManager
            adapter = citiesAdapter
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