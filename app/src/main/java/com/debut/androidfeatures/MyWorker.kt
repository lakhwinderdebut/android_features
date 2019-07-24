package com.debut.androidfeatures

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * MyWorker class will do the work in background i.e to show notification
 * we can also pass data to worker class and send data back once process is completed
 */
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    companion object {
        var SUCCESS = "Success"
    }

    override fun doWork(): Result {
        val data = inputData
        data.getString(MainActivity.TITLE)?.let { displayNotification(it, "work is finished") }
        val data1 = Data.Builder().putString(SUCCESS, "Task finished").build()
        return Result.success(data1)
    }

    /**
     * This method will show notification
     */
    private fun displayNotification(task: String, desc: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            val channel =
                NotificationChannel("shivam", "shivamsharma", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, "shivam")
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.drawable.ic_feature)

        manager.notify(1, builder.build())

    }
}