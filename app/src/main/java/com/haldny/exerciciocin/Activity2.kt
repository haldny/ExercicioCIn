package com.haldny.exerciciocin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Activity2 : AppCompatActivity() {

    private lateinit var button2: Button
    private lateinit var buttonStop2: Button

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

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        buttonStop2 = findViewById(R.id.button_stop2)
        buttonStop2.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
    }

}