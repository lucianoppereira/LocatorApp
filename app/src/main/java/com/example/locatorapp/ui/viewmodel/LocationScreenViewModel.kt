package com.example.locatorapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locatorapp.domain.model.LocationModel
import com.example.locatorapp.domain.model.Resource
import com.example.locatorapp.domain.usecase.GetLocationsUseCase
import com.example.locatorapp.domain.usecase.SaveLocationUseCase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) : ViewModel() {

    private var _locations = mutableStateOf<List<LocationModel>>(emptyList())
    val locations = _locations

    private var _marker = mutableStateOf<LocationModel?>(null)
    val marker = _marker

    private var _searchBarActive = mutableStateOf(false)
    val searchBarActive = _searchBarActive

    private var _searchState = mutableStateOf<Boolean?>(null)
    val searchState = _searchState

    private var _query = mutableStateOf("")
    val query = _query

    private var _favFilterState = mutableStateOf(false)
    val favFilterState = _favFilterState

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationsUseCase().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _searchState.value = true
                            _locations.value = result.data.sortedBy { it.title }
                        }
                    }
                    is Resource.Error -> { _searchState.value = false }
                    else -> { _searchState.value = null }
                }
            }
        }
    }

    fun filteredLocations(
        locations: List<LocationModel>,
        prefix: String = "",
        favFilter: Boolean = false
    ): List<LocationModel> {
        var filteredLocations = locations
        if (prefix.isNotEmpty()) {
            filteredLocations = locations.filter { it.title.lowercase().startsWith(prefix.lowercase()) }
        }

        return if (favFilter) filteredLocations.filter { it.isFavorite } else filteredLocations
    }

    fun saveToFavorites(location: LocationModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val favLocation = location.copy(isFavorite = !location.isFavorite)
            _marker.value = favLocation
            _locations.value = _locations.value.map {
                if (it.id == location.id) {
                    saveLocationUseCase.invoke(favLocation)
                    favLocation
                } else {
                    it
                }
            }
        }
    }

    fun setFavFilter() {
        _favFilterState.value = !_favFilterState.value
    }

    fun updateSearchState(state: Boolean) {
        viewModelScope.launch {
            _searchBarActive.value = state
        }
    }

    fun updateMarkerPosition(cameraPositionState: CameraPositionState, markedLocation: LocationModel? = null) {
        viewModelScope.launch {
            _marker.value = markedLocation.also {
                it?.let {
                    animateMarkerChange(cameraPositionState, LatLng(it.latitude, it.longitude))
                }
            }
        }
    }

    private suspend fun animateMarkerChange(cameraPositionState: CameraPositionState, coordinates: LatLng) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(coordinates , 10f))
    }

    fun setQuery(query: String = "") {
        _query.value = query
    }
}
