package com.haldny.exerciciocin

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var buttonStop: Button

    private lateinit var textViewActivity1: TextView

    private lateinit var broadcast: BroadcastReceiver

    private lateinit var broadcastWifi: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MyService::class.java)
        intent.putExtra("ACTIVITY_TEXT", getString(R.string.activity1))

        startService(intent)

        broadcast = ScreenOnOffReceiver()
        broadcastWifi = WifiChangedReceiver()
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(this, "onRestart foi chamado", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()

        textViewActivity1 = findViewById(R.id.textView)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        buttonStop = findViewById(R.id.button_stop)
        buttonStop.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        registerReceiver(broadcast, IntentFilter(Intent.ACTION_SCREEN_OFF))
        registerReceiver(broadcast, IntentFilter(Intent.ACTION_SCREEN_ON))

        registerReceiver(broadcastWifi, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))

        val contatos = BuscaContato.lerContatos(this)

        textViewActivity1.text = contatos.get(0).toString()

        contatos.forEach {
            Log.d("Turma22", "Contato encontrado foi: $it")
        }
    }

    override fun onStop() {
        super.onStop()

        if (broadcast != null) {
            unregisterReceiver(broadcast)
        }

        if (broadcastWifi != null) {
            unregisterReceiver(broadcastWifi)
        }
    }

    inner class ScreenOnOffReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {

            if (Intent.ACTION_SCREEN_OFF.equals(intent?.action)) {
                Log.d("Turma22", "A tela foi desligada")
            } else if (Intent.ACTION_SCREEN_ON.equals(intent?.action)) {
                Log.d("Turma22", "A tela foi ligada")
            } else {
                Log.d("Turma22", "Action recebida nao foi screen on ou screen off")
            }

        }

    }

    inner class WifiChangedReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent?.action)) {
                Log.d("Turma22", "O estado do Wi-Fi mudou")
            } else {
                Log.d("Turma22", "O estado do Wi-Fi nao mudou")
            }
        }
    }

}