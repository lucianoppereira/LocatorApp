package com.example.locatorapp.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationApi @Inject constructor(private val api: LocationClient) {

    suspend fun getLocationList(): List<LocationDto> {
        return withContext(Dispatchers.IO) {
            val response = api.getLocations()
            response.body() ?: emptyList()
        }
    }
}
