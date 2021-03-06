package com.haldny.exerciciocin

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Activity2 : AppCompatActivity() {

    private lateinit var button2: Button
    private lateinit var buttonStop2: Button
    private lateinit var buttonShowBattery: Button

    private lateinit var textViewActivity2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val intent = Intent(this, MyService::class.java)
        intent.putExtra("ACTIVITY_TEXT", getString(R.string.activity2))

        startService(intent)
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(this, "onRestart foi chamado", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()

        textViewActivity2 = findViewById(R.id.textView2)

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        buttonStop2 = findViewById(R.id.button_stop2)
        buttonStop2.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        val contatos = BuscaContato.lerContatos(this)
        textViewActivity2.text = contatos.get(1).toString()

        buttonShowBattery = findViewById(R.id.buttonShowBattery)
        buttonShowBattery.setOnClickListener {
            bindService(Intent(this, MyService::class.java), object : ServiceConnection {

                override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?) {
                    val myService = binder as MyService.MyBinder
                    myService.getService().showBatteryStatus()
                }

                override fun onServiceDisconnected(componentName: ComponentName?) {
                    unbindService(this)
                }
            }, Context.BIND_AUTO_CREATE)
        }
    }

}