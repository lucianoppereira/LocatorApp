package com.example.locatorapp.data.remote

import com.example.locatorapp.domain.model.LocationModel
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("_id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coordinates: Coordinates
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

fun LocationDto.toLocationModel(): LocationModel {
    return LocationModel(
        id = id,
        title = "$name, $country",
        subtitle = "${coordinates.lat}, ${coordinates.lon}",
        latitude = coordinates.lat,
        longitude = coordinates.lon
    )
}
