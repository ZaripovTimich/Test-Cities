package com.example.testcities.ui.listcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcities.data.models.CitiesResponse
import com.example.testcities.data.repository.MainRepositoryImpl
import com.example.testcities.data.repository.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val repository: MainRepositoryImpl
) : ViewModel() {

    private var _citiesMutable = MutableStateFlow<NetworkResult<CitiesResponse>>(NetworkResult.Loading())
    val stateCities: StateFlow<NetworkResult<CitiesResponse>> = _citiesMutable

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getCities().collect()
//        }
//    }

    fun getCities() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCities().collect()
    }
}