package com.ferdinand.workmanagerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ferdinand.workmanagerdemo.ui.theme.WorkManagerDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StartNotificationWorkerButton(onClick = {
                            startNotificationWork()
                        })
                    }
                }
            }
        }
    }

    private fun startNotificationWork() {
        val constraints = Constraints.Builder()
            .apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
            setRequiresBatteryNotLow(true)
        }.build()

        val notifWorkRequest = OneTimeWorkRequestBuilder<NotificationWork>()
            .setConstraints(constraints)
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(notifWorkRequest)
    }
}

@Composable
fun StartNotificationWorkerButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Run Notification Work")
    }
}
