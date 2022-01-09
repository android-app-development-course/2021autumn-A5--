package com.example.fanlaisu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class name : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        supportActionBar?.hide()
    }
}