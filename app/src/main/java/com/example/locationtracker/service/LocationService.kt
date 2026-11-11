package com.example.locationtracker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.locationtracker.notification.NotificationHelper

class LocationService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        NotificationHelper.createNotificationChannel(this)
        startForeground(
            1,
            NotificationHelper.createNotification(this)
        )
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}

