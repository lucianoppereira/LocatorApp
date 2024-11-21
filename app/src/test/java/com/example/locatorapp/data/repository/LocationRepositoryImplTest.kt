package com.example.locatorapp.data.repository

import com.example.locatorapp.data.local.LocationDao
import com.example.locatorapp.data.local.LocationEntity
import com.example.locatorapp.data.remote.LocationApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LocationRepositoryImplTest {

    @RelaxedMockK
    private lateinit var api: LocationApi

    @RelaxedMockK
    private lateinit var locationDao: LocationDao

    lateinit var repository: LocationRepositoryImpl

    @Before
    fun doBefore() {
        MockKAnnotations.init(this)
        repository = LocationRepositoryImpl(api, locationDao)
    }

    @Test
    fun `when database is empty return anything get values from api`() = runBlocking {
        // Given
        coEvery { repository.getAllLocationsFromLocal() } returns listOf()

        // When
        repository.getAllLocations()

        // Then
        coVerify(exactly = 1) {
            api.getLocationList()
        }
    }

    @Test
    fun `when database has content returns it`() = runBlocking {
        // Given
        val mockItems = mockApiResponse()
        coEvery { locationDao.getAllLocations() } returns mockItems

        // When
        val items = repository.getAllLocations()

        // Then
        coVerify(exactly = 0) { api.getLocationList() }
        coVerify(exactly = 1) { locationDao.getAllLocations() }
        assertTrue(items.isNotEmpty())
    }

    private fun mockApiResponse() = mutableListOf(
        LocationEntity(
            123,
            "New York, US",
            11111.0,
            11111.0,
            false
        ),
        LocationEntity(
            133,
            "Buenos Aires, AR",
            22222.0,
            22222.0,
            false
        )
    )
}