package com.example.locationtracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.maplibre.android.MapLibre

@HiltAndroidApp
class LocationTrackerApp : Application(){
    override fun onCreate() {
        super.onCreate()

        MapLibre.getInstance(this)
    }
}