package com.example.locatorapp.di

import android.content.Context
import androidx.room.Room
import com.example.locatorapp.data.local.LocationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): LocationsDatabase {
        return Room.databaseBuilder(
            appContext,
            LocationsDatabase::class.java,
            "locations_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocationDao(db: LocationsDatabase) = db.locationDao()
}