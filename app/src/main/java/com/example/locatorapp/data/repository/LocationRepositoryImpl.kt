package com.example.locatorapp.data.repository

import com.example.locatorapp.data.local.LocationDao
import com.example.locatorapp.data.local.toLocationModel
import com.example.locatorapp.data.remote.LocationApi
import com.example.locatorapp.data.remote.toLocationModel
import com.example.locatorapp.domain.model.LocationModel
import com.example.locatorapp.domain.model.toLocationEntity
import com.example.locatorapp.domain.repository.LocalStorageRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationApi,
    private val locationDao: LocationDao
) : LocalStorageRepository {

    suspend fun getAllLocations(): List<LocationModel> {
        if (getAllLocationsFromLocal().isNullOrEmpty()) {
            val locations = getAllLocationsFromRemote()
            saveAllLocations(locations)
        }
        return getAllLocationsFromLocal()
    }

    suspend fun getAllLocationsFromRemote(): List<LocationModel> =
        api.getLocationList().map { it.toLocationModel() }

    override suspend fun saveLocation(location: LocationModel) {
        try {
            locationDao.insertLocation(location.toLocationEntity())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateLocation(location: LocationModel) {
        try {
            locationDao.updateLocation(location.toLocationEntity())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun saveAllLocations(locations: List<LocationModel>) {
        try {
            locationDao.insertAllLocations(
                locations.map { it.toLocationEntity() }
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllLocationsFromLocal(): List<LocationModel> {
        try {
            return locationDao.getAllLocations().map { it.toLocationModel() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getLocationsByFavorite(): List<LocationModel> {
        try {
            return locationDao.getLocationsByFav().map { it.toLocationModel() }
        } catch (e: Exception) {
            throw e
        }
    }
}