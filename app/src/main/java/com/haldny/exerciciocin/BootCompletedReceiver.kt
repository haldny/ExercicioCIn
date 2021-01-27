package com.haldny.exerciciocin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent?.action)) {
            Log.d("Turma22", "Aconteceu o Boot Completed")
        } else {
            Log.d("Turma22", "Action recebida nao foi Boot Completed")
        }

    }

}