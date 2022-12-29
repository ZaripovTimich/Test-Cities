package com.example.testcities.ui.cityinformation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testcities.R
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.databinding.CityInfoFragmentBinding

class CityInfoFragment : Fragment(R.layout.city_info_fragment) {

    private lateinit var binding: CityInfoFragmentBinding
    private val viewModel by viewModels<CityInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityId = requireArguments().getInt(ARG_CITY_ID)
        viewModel.getInfoCity(cityId)
        binding = CityInfoFragmentBinding.bind(view)
        //TODO add Flow observe

        binding.btnMore.setOnClickListener {

        }
    }

    private fun initDetails(info: CityInfoResponse) {
        binding.apply {
            with(info) {
                tvCity.text = city
                tvCountry.text = country
                tvCountryCode.text = countryCode
                tvPopulation.text = population.toString()
                tvElevationMeters.text = elevationMeters.toString()
            }
        }
    }

    companion object {
        const val ARG_CITY_ID = "city_id"
    }
}