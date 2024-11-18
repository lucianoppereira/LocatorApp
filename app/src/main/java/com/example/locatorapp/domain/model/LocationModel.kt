package com.example.locatorapp.domain.model

import com.example.locatorapp.data.local.LocationEntity

data class LocationModel(
    val id: Long,
    val title: String,
    val subtitle: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false
)

fun LocationModel.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        title = title,
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite
    )
}
