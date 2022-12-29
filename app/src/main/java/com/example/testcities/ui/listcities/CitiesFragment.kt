package com.example.testcities.ui.listcities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testcities.R
import com.example.testcities.databinding.CitiesFragmentBinding

class CitiesFragment : Fragment(R.layout.cities_fragment) {

    private lateinit var binding: CitiesFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CitiesFragmentBinding.bind(view)
    }
}