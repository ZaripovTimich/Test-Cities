package com.example.testcities.ui.cityinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcities.data.models.CityInfoResponse
import com.example.testcities.data.repository.MainRepositoryImpl
import com.example.testcities.data.repository.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInfoViewModel @Inject constructor(
    private val repository: MainRepositoryImpl
) : ViewModel() {

    private var _cityMutable =
        MutableStateFlow<NetworkResult<CityInfoResponse>>(NetworkResult.Loading())
    val stateCity: StateFlow<NetworkResult<CityInfoResponse>> = _cityMutable.asStateFlow()

    fun getInfoCity(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getInfoCity(id).collect { result ->
            _cityMutable.value = result
        }
    }
}