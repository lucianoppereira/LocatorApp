package com.example.locatorapp.di

import com.example.locatorapp.data.repository.LocationRepositoryImpl
import com.example.locatorapp.domain.repository.LocalStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLocalStorageRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocalStorageRepository
}