package com.example.fanlaisu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Wallet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet)

        supportActionBar?.hide()
    }
}