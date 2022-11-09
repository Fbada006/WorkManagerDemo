package com.ferdinand.workmanagerdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

object NotificationUtils {

    private const val GADS_REFRESH_NOTIFICATION_CHANNEL_ID = "com.ferdinand.workmanagerdemo.gadschannelid"
    private const val GADS_REFRESH_NOTIFICATION_ID = 342

    /**Send out the notification to the user
     * @param context is context
     * @param message is the actual message for the notification
     * */
    fun sendNotification(context: Context, message: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                GADS_REFRESH_NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.main_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(mChannel)
        }

        //Use builder pattern to create the notification
        val notificationBuilder =
            NotificationCompat.Builder(context, GADS_REFRESH_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.purple_200))
                .setSmallIcon(R.drawable.work_manager_poster)
                .setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.drawable.work_manager_poster
                        )
                    )
                )
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
        }

        notificationManager.notify(GADS_REFRESH_NOTIFICATION_ID, notificationBuilder.build())
    }
}
