package com.example.locatorapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locatorapp.domain.model.LocationModel
import com.example.locatorapp.domain.usecase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private var _locations = mutableStateOf<List<LocationModel>>(emptyList())
    val locations = _locations

    private var _searchBarState = mutableStateOf(false)
    val searchBarState = _searchBarState

    private var _query = mutableStateOf("")
    val query = _query

    private var _favFilterState = mutableStateOf(false)
    val favFilterState = _favFilterState


    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getLocationsUseCase.invoke()
            _locations.value = result.sortedBy { it.title }
        }
    }

    fun setFavState() {
        _favFilterState.value = !_favFilterState.value
    }

    fun updateSearchState(state: Boolean) {
        viewModelScope.launch {
            _searchBarState.value = state
        }
    }
}