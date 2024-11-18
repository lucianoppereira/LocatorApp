package com.example.locatorapp.domain.repository

import com.example.locatorapp.domain.model.LocationModel

interface LocalStorageRepository {

    suspend fun saveLocation(location: LocationModel)
    suspend fun updateLocation(location: LocationModel)
    suspend fun saveAllLocations(locations: List<LocationModel>)
    suspend fun getAllLocationsFromLocal(): List<LocationModel>
    suspend fun getLocationsByFavorite(): List<LocationModel>
}