package com.example.locatorapp.domain.usecase

import com.example.locatorapp.domain.model.LocationModel
import com.example.locatorapp.domain.repository.LocalStorageRepository
import javax.inject.Inject

class SaveLocationUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {

    suspend operator fun invoke(location: LocationModel) {
        repository.updateLocation(location)
    }
}