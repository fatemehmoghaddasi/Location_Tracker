package com.example.locationtracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.locationtracker.data.local.dao.LocationDao
import com.example.locationtracker.data.local.entity.LocationEntity

@Database(
    entities = [LocationEntity::class], version = 1, exportSchema = false
)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
