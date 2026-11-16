package com.example.locationtracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.locationtracker.ui.model.LocationModel

@Entity(tableName = "location_tracker")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val accuracy: Float
)

fun LocationEntity.mapToLocationModel() = LocationModel(
    id = id,
    latitude = latitude,
    longitude = longitude,
    timestamp = timestamp,
    accuracy = accuracy
)
