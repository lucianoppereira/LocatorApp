package com.example.locatorapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.locatorapp.domain.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor() : ViewModel() {
    private var _locations = mutableStateOf<List<LocationModel>>(emptyList())

    val locations = _locations
    private var _searchBarState = mutableStateOf(false)

    val searchBarState = _searchBarState
    private var _query = mutableStateOf("")

    val query = _query
    private var _favFilterState = mutableStateOf(false)

    val favFilterState = _favFilterState


    fun getLocations() {
        _locations.value = listOf(
            LocationModel(
                "title",
                "substitle",
                1.1111,
                1.2222,
                false
            ),
            LocationModel(
                "title",
                "substitle",
                1.1111,
                1.2222,
                false
            )
        )
    }

    fun setFavState() {
        _favFilterState.value = !_favFilterState.value
    }
}