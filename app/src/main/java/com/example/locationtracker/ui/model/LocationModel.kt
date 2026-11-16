package com.example.locationtracker.ui.model

import com.example.locationtracker.data.local.entity.LocationEntity

data class LocationModel(
    val id : Int,
    val latitude : Double,
    val longitude : Double,
    val timestamp: Long,
    val accuracy : Float
)

fun LocationModel.mapToLocationEntity() = LocationEntity(
    id = id,
    latitude = latitude,
    longitude = longitude,
    timestamp = timestamp,
    accuracy = accuracy

)
