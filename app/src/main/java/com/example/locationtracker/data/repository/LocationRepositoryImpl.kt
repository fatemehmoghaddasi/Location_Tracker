package com.example.locationtracker.data.repository

import com.example.locationtracker.data.local.dao.LocationDao
import com.example.locationtracker.data.local.entity.mapToLocationModel
import com.example.locationtracker.ui.model.LocationModel
import com.example.locationtracker.ui.model.mapToLocationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(private val locationDao: LocationDao) : LocationRepository {

    override fun getAllLocations(): Flow<List<LocationModel>> {
        return locationDao.getAllLocations().map { entities ->
            entities.map { it.mapToLocationModel() }
        }
    }

    override suspend fun deleteAllLocations() {
        locationDao.deleteAllLocations()
    }

    override suspend fun insertLocation(locationModel: LocationModel) {
        locationDao.insertLocation(locationModel.mapToLocationEntity())
    }

    override suspend fun deleteLocation(locationModel: LocationModel) {
        locationDao.deleteLocation(locationModel.mapToLocationEntity())
    }
}
