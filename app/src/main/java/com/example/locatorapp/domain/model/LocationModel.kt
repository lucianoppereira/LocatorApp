package com.example.locatorapp.domain.model

data class LocationModel(
    val title: String,
    val subtitle: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false
)
