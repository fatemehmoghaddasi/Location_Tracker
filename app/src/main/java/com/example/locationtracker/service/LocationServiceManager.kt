package com.example.locationtracker.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
@Singleton
class LocationServiceManager@Inject constructor(
   @param: ApplicationContext private val context: Context
) {
    private val _isTracking = MutableStateFlow(false)
    val isTracking : StateFlow<Boolean> = _isTracking.asStateFlow()

    fun updateTrackingState(isTracking : Boolean){
        _isTracking.value = isTracking
    }
    fun startLocationService() {
        val intent = Intent(context, LocationService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    fun stopLocationService() {
        val intent = Intent(context, LocationService::class.java)
        context.stopService(intent)
        updateTrackingState(false)
    }
}
