package com.example.locationtracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.locationtracker.data.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_tracker ORDER BY timestamp DESC")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query("DELETE FROM location_tracker")
    suspend fun deleteAllLocations()

    @Insert
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Delete
    suspend fun deleteLocation(locationEntity: LocationEntity)
}

