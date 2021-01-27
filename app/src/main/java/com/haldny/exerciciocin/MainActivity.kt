package com.haldny.exerciciocin

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
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var buttonStop: Button

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

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        buttonStop = findViewById(R.id.button_stop)
        buttonStop.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_SCREEN_ON)

        registerReceiver(broadcast, filter)

        val filterWifi = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(broadcastWifi, filterWifi)

        val contatos = lerContatos()
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

    private fun lerContatos() : List<Contato> {
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME)

        val indexID = cursor?.getColumnIndex(ContactsContract.Contacts._ID)

        val indexPhone = cursor?.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)

        val indexName = cursor?.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

        val contatos = mutableListOf<Contato>()

        while (cursor?.moveToNext() == true) {
            val id = indexID?.let { cursor?.getString(it) }
            val name = indexName?.let { cursor?.getString(it) }

            val phones = indexPhone?.let { cursor?.getString(it) }
            val phoneCount = phones?.toInt() ?: 0
            if (phoneCount > 0) {
                //TODO: Get Phones
            }

            val contato = Contato(id, name, listOf())
            contatos.add(contato)
        }

        cursor?.close()

        return contatos
    }

}