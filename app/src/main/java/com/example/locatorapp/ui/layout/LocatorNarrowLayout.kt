@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.locatorapp.ui.layout

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.locatorapp.ui.components.LocationInfo
import com.example.locatorapp.ui.components.LocationItem
import com.example.locatorapp.ui.components.favIconButton
import com.example.locatorapp.ui.viewmodel.LocationScreenViewModel

@Composable
fun LocatorNarrowLayout(viewModel: LocationScreenViewModel = hiltViewModel()) {

    val locations by remember { viewModel.locations }
    var query by remember { viewModel.query }
    var active by remember { viewModel.searchBarState }
    var favFilter by remember { viewModel.favFilterState }

    viewModel.getLocations()

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
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            }
                        },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = { query = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Close info",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                favIconButton(isFavorite = false) {
                                    favFilter = !favFilter
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
                    items(locations) {
                        LocationItem(
                            it,
                            onFavClick = {
                                favFilter = !favFilter
                            }
                        ) {
                        }
                    }
                }
            }
        },
        bottomBar = {
            //LocationInfo(
            //    location,
            //    onFavClick = {
            //        viewModel.setFavState()
            //    }
            //) {
            //}
        }
    ) {

    }
}