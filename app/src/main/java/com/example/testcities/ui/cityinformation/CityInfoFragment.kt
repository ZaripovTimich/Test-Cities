package com.example.testcities.ui.cityinformation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testcities.R
import com.example.testcities.databinding.CityInfoFragmentBinding

class CityInfoFragment : Fragment(R.layout.city_info_fragment) {

    private lateinit var binding: CityInfoFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CityInfoFragmentBinding.bind(view)
    }
}