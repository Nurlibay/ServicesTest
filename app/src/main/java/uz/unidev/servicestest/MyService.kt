package uz.unidev.servicestest

import android.app.Service
import android.content.Context
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
        val start = intent?.getIntExtra(EXTRA_START, 0)?: 0
        coroutineScope.launch {
            for(i in start until start + 10){
                delay(1000)
                log("Timer: $i")
            }
        }
        /**
         *  - START_STICKY
         *  - START_NOT_STICKY
         *  - START_REDELIVER_INTENT
         */
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    companion object {
        private const val EXTRA_START = "start"
        fun newIntent(context: Context, start: Int): Intent{
            return Intent(context, MyService::class.java).apply {
                putExtra(EXTRA_START, start)
            }
        }
    }

    private fun log(message: String){
        Log.d("SERVICE_TAG", "MyService: $message")
    }
}