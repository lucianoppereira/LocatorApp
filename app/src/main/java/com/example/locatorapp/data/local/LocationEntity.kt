package com.example.locatorapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.locatorapp.domain.model.LocationModel

@Entity(tableName = "saved_locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    var id: Long,
    @ColumnInfo("title")
    var title: String,
    @ColumnInfo("latitude")
    var latitude: Double,
    @ColumnInfo("longitude")
    var longitude: Double,
    @ColumnInfo("isFavorite")
    var isFavorite: Boolean = false
)

fun LocationEntity.toLocationModel(): LocationModel {
    return LocationModel(
        id = id,
        title = title,
        subtitle = "$latitude, $longitude",
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite
    )
}
