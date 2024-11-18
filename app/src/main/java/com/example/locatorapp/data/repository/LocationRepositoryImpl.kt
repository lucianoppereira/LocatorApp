package com.example.locatorapp.data.repository

import com.example.locatorapp.data.remote.LocationApi
import com.example.locatorapp.data.remote.toLocationModel
import com.example.locatorapp.domain.model.LocationModel
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationApi
) {

    suspend fun getAllLocationsFromRemote(): List<LocationModel> =
        api.getLocationList().map { it.toLocationModel() }
}