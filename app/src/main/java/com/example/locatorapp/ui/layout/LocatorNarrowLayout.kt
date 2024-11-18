@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.locatorapp.ui.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.locatorapp.ui.components.LocationInfo
import com.example.locatorapp.ui.components.LocationItem
import com.example.locatorapp.ui.components.favIconButton
import com.example.locatorapp.ui.viewmodel.LocationScreenViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocatorNarrowLayout(viewModel: LocationScreenViewModel = hiltViewModel()) {

    val locations by remember { viewModel.locations }
    var query by remember { viewModel.query }
    var active by remember { viewModel.searchBarState }
    var favFilter by remember { viewModel.favFilterState }
    val marker by remember { viewModel.marker }
    val cameraPositionState = rememberCameraPositionState()

    val lifeCycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getLocations()
        }
    }

    Scaffold(
        topBar = {
            DockedSearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = { viewModel.updateSearchState(false) },
                        expanded = active,
                        placeholder = { Text(text = "Search") },
                        onExpandedChange = { viewModel.updateSearchState(it) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = { viewModel.setQuery() }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Close info",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                favIconButton(isFavorite = favFilter) {
                                    viewModel.updateSearchState(true)
                                    viewModel.setFavFilter()
                                }
                            }
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                expanded = active,
                onExpandedChange = { viewModel.updateSearchState(it) }
            ) {
                LazyColumn {
                    items(viewModel.filteredLocations(locations, query, favFilter)) {
                        LocationItem(
                            it,
                            onFavClick = { viewModel.saveToFavorites(it) }
                        ) {
                            viewModel.updateMarkerPosition(cameraPositionState, it)
                            viewModel.updateSearchState(false)
                        }
                    }
                }
            }
        },
        bottomBar = {
            LocationInfo(
                marker,
                onFavClick = {
                    marker?.let { viewModel.saveToFavorites(it) }
                }
            ) {
                viewModel.updateMarkerPosition(cameraPositionState)
            }
        }
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            onMapClick = {
                viewModel.updateSearchState(false)
            }
        ) {
            marker?.let {
                val position = LatLng(it.latitude, it.longitude)
                Marker(
                    state = MarkerState(position),
                    title = it.title,
                    snippet = it.subtitle
                )
            }
        }
    }
}
