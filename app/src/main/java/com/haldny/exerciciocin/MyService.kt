package com.haldny.exerciciocin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("ACTIVITY_TEXT").let {
            Toast.makeText(this, "Activity: $it foi inicializada", Toast.LENGTH_LONG).show()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Service foi parado", Toast.LENGTH_LONG).show()
    }

}