package com.viach.navigationInViewModel.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.domain.entity.Notification
import javax.inject.Inject

class NotificationDisplayer @Inject constructor(
    private val context: Context
) {

    private val notificationManager: NotificationManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                DEFAULT_CHANNEL_ID,
                DEFAULT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
        }

        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun showNotification(
        notification: Notification,
        pendingIntent: PendingIntent? = null
    ) {
        NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setContentTitle("title")
            .setContentText(notification.message)
            .setSmallIcon(R.drawable.ic_info)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
            .also {
                notificationManager.notify(notification.id, it)
            }
    }

    companion object {
        private const val DEFAULT_CHANNEL_ID = "DEFAULT_CHANNEL_ID"
        private const val DEFAULT_CHANNEL_NAME = "DEFAULT CHANNEL"
    }
}