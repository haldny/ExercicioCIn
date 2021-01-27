package com.haldny.exerciciocin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var buttonStop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("ACTIVITY_TEXT", getString(R.string.activity1))

        startService(intent)
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
    }

}