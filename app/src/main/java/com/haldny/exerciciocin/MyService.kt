package com.haldny.exerciciocin

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import android.widget.Toast

class MyService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
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

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Service foi parado", Toast.LENGTH_LONG).show()
    }

    fun showBatteryStatus() {
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        val capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        Toast.makeText(this, "Capacidade da bateria: $capacity", Toast.LENGTH_LONG).show()
    }

    inner class MyBinder: Binder() {

        fun getService(): MyService {
            return this@MyService
        }

    }

}