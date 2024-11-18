package com.example.locatorapp.domain.usecase

import com.example.locatorapp.data.repository.LocationRepositoryImpl
import com.example.locatorapp.domain.model.LocationModel
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationRepositoryImpl
) {

    suspend operator fun invoke(): List<LocationModel> = repository.getAllLocationsFromRemote()
}