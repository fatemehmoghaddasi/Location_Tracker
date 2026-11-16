package com.example.locationtracker.data.repository

import com.example.locationtracker.ui.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocations(): Flow<List<LocationModel>>
    suspend fun deleteAllLocations()
    suspend fun insertLocation(locationModel: LocationModel)
    suspend fun deleteLocation(locationModel: LocationModel)
}
