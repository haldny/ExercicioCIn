package com.haldny.exerciciocin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity3 : AppCompatActivity() {

    private lateinit var buttonStop3: Button

    private lateinit var textViewActivity3: TextView

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

        textViewActivity3 = findViewById(R.id.textView3)

        val contatos = BuscaContato.lerContatos(this)
        textViewActivity3.text = contatos.get(2).toString()
    }

}