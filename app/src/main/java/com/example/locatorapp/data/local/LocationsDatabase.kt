package com.example.locatorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1)
abstract class LocationsDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
}
