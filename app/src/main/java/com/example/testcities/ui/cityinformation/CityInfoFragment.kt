package com.example.testcities.ui.cityinformation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testcities.R
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.data.repository.NetworkResult
import com.example.testcities.databinding.CityInfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoFragment : Fragment(R.layout.city_info_fragment) {

    private lateinit var binding: CityInfoFragmentBinding
    private val viewModel by viewModels<CityInfoViewModel>()
    private var wikiDataId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityId = requireArguments().getInt(ARG_CITY_ID)
        viewModel.getInfoCity(cityId)
        binding = CityInfoFragmentBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.stateCity.collect { response ->
                response.data?.let {
                    when (response) {
                        is NetworkResult.Success -> {
                            hideLoading()
                            initDetails(response.data)
                        }
                        is NetworkResult.Error -> {
                            hideLoading()
                            Toast.makeText(
                                context,
                                getText(R.string.error),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is NetworkResult.Loading -> showLoading()
                    }
                }
            }
        }

        binding.btnMore.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://www.wikidata.org/wiki/$wikiDataId")
            }
            context?.startActivity(intent)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initDetails(info: CityInfoResponse) {
        binding.apply {
            with(info.data) {
                tvCity.text = city
                tvCountry.text = country
                tvCountryCode.text = countryCode
                tvPopulation.text = population.toString()
                tvElevationMeters.text = elevationMeters.toString()
            }
        }
        wikiDataId = info.data.wikiDataId
    }

    private fun showLoading() {
        binding.pbContainer.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbContainer.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    companion object {
        const val ARG_CITY_ID = "city_id"
    }
}