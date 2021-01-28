package com.haldny.exerciciocin

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("ACTIVITY_TEXT").let {
            Toast.makeText(this, "Activity: $it foi inicializada", Toast.LENGTH_LONG).show()
        }

        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = activityManager.getRunningTasks(Int.MAX_VALUE)
        if (tasks != null && tasks.size > 0) {
            Toast.makeText(this,
                    "Activity rodando é ${tasks.get(0).topActivity?.className}", Toast.LENGTH_LONG).show()
        }

        val packageManager = packageManager

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Service foi parado", Toast.LENGTH_LONG).show()
    }

}