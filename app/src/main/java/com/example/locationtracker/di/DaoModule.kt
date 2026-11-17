package com.example.locationtracker.di

import com.example.locationtracker.data.local.LocationDatabase
import com.example.locationtracker.data.local.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun providesDaoModule(database: LocationDatabase): LocationDao {
        return database.locationDao()
    }
}
