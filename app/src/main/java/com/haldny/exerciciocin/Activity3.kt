package com.haldny.exerciciocin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity3 : AppCompatActivity() {

    private lateinit var buttonStop3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)


        val intent = Intent(this, MyService::class.java)
        intent.putExtra("ACTIVITY_TEXT", getString(R.string.activity3))

        startService(intent)

        buttonStop3 = findViewById(R.id.button_stop3)
        buttonStop3.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
    }

}