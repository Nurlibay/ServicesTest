package uz.unidev.servicestest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

/**
 *  Created by Nurlibay Koshkinbaev on 30/09/2022 00:15
 */

class MyService: Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
            for(i in 0 until 10){
                delay(1000)
                log("Timer: $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    private fun log(message: String){
        Log.d("SERVICE_TAG", "MyService: $message")
    }
}