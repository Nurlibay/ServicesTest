package uz.unidev.servicestest

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

/**
 *  Created by Nurlibay Koshkinbaev on 30/09/2022 00:15
 */

class MyIntentService: IntentService(NAME) {

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for(i in 0 until 5){
            Thread.sleep(1000)
            log("Timer: $i")
        }
    }

    override fun onCreate() {
        super.onCreate()
        setIntentRedelivery(false)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }
    
    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Title")
            .setContentText("This content text")
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String){
        Log.d("SERVICE_TAG", "MyForegroundService: $message")
    }

    companion object {
        fun newIntent(context: Context): Intent{
            return Intent(context, MyIntentService::class.java)
        }
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        private const val NAME = "MyIntentService"
    }
}