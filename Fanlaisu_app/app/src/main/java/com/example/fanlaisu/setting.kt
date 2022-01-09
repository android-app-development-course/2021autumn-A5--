package com.example.fanlaisu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar?.hide()

        var b_safey: Button = findViewById(R.id.button1)

        b_safey.setOnClickListener {
            val intent = Intent(this,safey::class.java)
            startActivity(intent)
        }
    }
}