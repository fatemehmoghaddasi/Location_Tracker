package com.example.locationtracker.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class LocationServiceManager@Inject constructor(@ApplicationContext private val context: Context) {
    fun startLocationService() {
        val intent = Intent(context, LocationService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    fun stopLocationService() {
        val intent = Intent(context, LocationService::class.java)
        context.stopService(intent)
    }
}
