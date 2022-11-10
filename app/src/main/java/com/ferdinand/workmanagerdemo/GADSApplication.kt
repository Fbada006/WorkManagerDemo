package com.ferdinand.workmanagerdemo

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class GADSApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    private fun initWorkManager() {
        val constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
            setRequiresBatteryNotLow(true)
        }.build()


        val repeatingMovieRefreshRequest =
            PeriodicWorkRequestBuilder<NotificationWork>(
                15, TimeUnit.MINUTES,
                5, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            NotificationWork.GADS_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingMovieRefreshRequest
        )
    }
}