package com.example.locatorapp.domain.usecase

import com.example.locatorapp.data.repository.LocationRepositoryImpl
import com.example.locatorapp.domain.model.LocationModel
import com.example.locatorapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationRepositoryImpl
) {

    operator fun invoke(): Flow<Resource<List<LocationModel>>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    data = repository.getAllLocations()
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(message = e.message ?: "Unknown Error")
            )
        }
    }
}
