package com.example.locationtracker.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.locationtracker.R

object NotificationHelper {

    const val CHANNEL_ID = "location_tracker"
    const val CHANNEL_NAME = "Location Tracker"

    fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(context: Context): Notification {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Location Tracker")
            .setContentText("Location tracker is active")
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        return notification
    }
}



