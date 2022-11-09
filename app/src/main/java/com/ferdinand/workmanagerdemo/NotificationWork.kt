package com.ferdinand.workmanagerdemo

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class NotificationWork(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    companion object {
        const val GADS_WORK_NAME = "com.ferdinand.workmanagerdemo.GadsNotifWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            NotificationUtils.sendNotification(
                applicationContext,
                applicationContext.getString(R.string.gads_work_starting)
            )

            // Simulate long running work
            delay(3000)

            NotificationUtils.sendNotification(
                applicationContext,
                applicationContext.getString(R.string.gads_work_successful)
            )

            Result.success()
        } catch (e: Exception) {
            NotificationUtils.sendNotification(
                applicationContext,
                applicationContext.getString(R.string.gads_work_failed)
            )
            Result.failure()
        }
    }
}