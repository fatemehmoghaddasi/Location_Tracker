package com.example.locationtracker.service

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.example.locationtracker.data.repository.LocationRepository
import com.example.locationtracker.notification.NotificationHelper
import com.example.locationtracker.ui.model.LocationModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationService : Service() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var locationServiceManager: LocationServiceManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    private val serviceScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var isTracking = false        //جلوگیری از ثبت درخواست تکراری لوکیشن

    override fun onCreate() {
        super.onCreate()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            30 * 60 * 1000L
        )
            .setMinUpdateIntervalMillis(30 * 60 * 1000L)
            .build()
        locationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val location = locationResult.lastLocation

                if (location != null) {
                    // موقعیت جدید دریافت شده
                    //serviceScope.launch {
                       // locationRepository.insertLocation(
                          //  LocationModel(
                               // id = 0,
                              //  latitude = location.latitude,
                               // longitude = location.longitude,
                               // timestamp = location.time,
                               // accuracy = location.accuracy
                            //)
                        //)
                  // }
                    saveLocation(location)

                }
            }
        }
    }
    private fun saveLocation(location: Location) {
        serviceScope.launch {
            locationRepository.insertLocation(
                LocationModel(
                    id = 0,
                    latitude = location.latitude,
                    longitude = location.longitude,
                    timestamp = location.time,
                    accuracy = location.accuracy
                )
            )
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationUpdates() {
        if (isTracking) return

        if (!hasLocationPermission()) {
            locationServiceManager.updateTrackingState(false)
            stopSelf()
            return
        }

        try {
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    saveLocation(location)
                }
            }.addOnFailureListener {
                it.printStackTrace()
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            ).addOnSuccessListener {
                isTracking = true
                locationServiceManager.updateTrackingState(true)
            }.addOnFailureListener {
                it.printStackTrace()
                locationServiceManager.updateTrackingState(false)
                stopSelf()
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            locationServiceManager.updateTrackingState(false)
            stopSelf()
        }
    }


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        if (!hasLocationPermission()) {
            stopSelf()
            return START_NOT_STICKY
        }
        NotificationHelper.createNotificationChannel(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ServiceCompat.startForeground(
                this,
                1,
                NotificationHelper.createNotification(this),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
            )
        } else {
            startForeground(
                1,
                NotificationHelper.createNotification(this)
            )
        }
        startLocationUpdates()

        return START_STICKY
    }

    override fun onDestroy() {
        fusedLocationClient.removeLocationUpdates(locationCallback)

        isTracking = false
        locationServiceManager.updateTrackingState(false)

        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}










