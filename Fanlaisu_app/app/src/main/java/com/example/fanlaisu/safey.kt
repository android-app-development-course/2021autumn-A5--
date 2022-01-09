package com.example.fanlaisu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class safey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safey)

        supportActionBar?.hide()
        var b_phone: Button = findViewById(R.id.button1)
        var b_email: Button = findViewById(R.id.button2)
        var b_pwd: Button = findViewById(R.id.button3)

        b_phone.setOnClickListener {
            val intent = Intent(this,phone::class.java)
            startActivity(intent)
        }

        b_email.setOnClickListener {
            val intent = Intent(this,email::class.java)
            startActivity(intent)
        }

        b_pwd.setOnClickListener {
            val intent = Intent(this,pwd::class.java)
            startActivity(intent)
        }
    }
}