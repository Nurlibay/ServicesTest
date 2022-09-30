package uz.unidev.servicestest

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.*

/**
 *  Created by Nurlibay Koshkinbaev on 30/09/2022 00:15
 */

class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        log("onStartJob")
        coroutineScope.launch {
            for (i in 0 until 10) {
                delay(1000)
                log("Timer: $i")
            }
        }
        jobFinished(params, true)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyService: $message")
    }
}