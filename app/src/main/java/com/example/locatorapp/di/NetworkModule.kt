package com.example.locatorapp.di

import com.example.locatorapp.BuildConfig
import com.example.locatorapp.data.remote.LocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesLocationClient(retrofit: Retrofit) =
        retrofit.create(LocationClient::class.java)
}
