package com.example.locatorapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationDao {

    @Query("SELECT * FROM saved_locations")
    suspend fun getAllLocations(): MutableList<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(locationEntities: List<LocationEntity>)

    @Update
    suspend fun updateLocation(locationEntity: LocationEntity)

    @Query("SELECT * FROM saved_locations WHERE isFavorite = true")
    suspend fun getLocationsByFav(): MutableList<LocationEntity>
}