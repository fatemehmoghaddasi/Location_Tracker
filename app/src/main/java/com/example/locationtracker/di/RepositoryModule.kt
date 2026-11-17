package com.example.locationtracker.di

import com.example.locationtracker.data.repository.LocationRepository
import com.example.locationtracker.data.repository.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsLocationRepository(locationRepository: LocationRepositoryImpl): LocationRepository

}
