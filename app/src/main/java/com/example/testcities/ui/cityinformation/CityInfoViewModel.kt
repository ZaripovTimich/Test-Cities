package com.example.testcities.ui.cityinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcities.data.repository.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInfoViewModel @Inject constructor(
    private val repository: MainRepositoryImpl
) : ViewModel() {


    fun getInfoCity(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getInfoCity(id)
    }
}