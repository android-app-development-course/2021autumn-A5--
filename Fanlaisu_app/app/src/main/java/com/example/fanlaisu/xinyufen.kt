package com.example.fanlaisu

import android.annotation.SuppressLint
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Message.obtain
import android.util.Log
import android.widget.ListView
import kotlinx.coroutines.delay
import java.lang.Thread.sleep


class xinyufen: AppCompatActivity() {

    var f = 1
    var score = 100
    var score2 = 0
    var c_times = 10
    var d_times = 0
    private val c_recordList = ArrayList<Record>()
    private val d_recordList = ArrayList<Record>()
    companion object {
        const val SCORE = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xinyufen)

        supportActionBar?.hide()
        val c_recordListView: ListView = findViewById(R.id.compliance_listView)
        val d_recordListView: ListView = findViewById(R.id.dishonest_listView)
        val cir:GradientProgressBar = findViewById(R.id.circle_progress_view4)

        val handler = Handler()
        val countDown = object : Runnable {

            override fun run() {
                cir.setPercent(score2)
                if (score2 != score) {
                    handler.postDelayed(this, 1)
                } else {

                }
                score2 = score2+1
            }
        }

        fun removeCountDOwn() {

            handler.removeCallbacks(countDown)
        }

        handler.postDelayed(countDown, 0)

        initCRecords()
        val c_adapter = recordAdapter(this,R.layout.record_list_item, c_recordList)
        c_recordListView.adapter = c_adapter
        initDRecords()
        val d_adapter = recordAdapter(this,R.layout.record_list_item, d_recordList)
        d_recordListView.adapter = d_adapter
    }

    private fun initCRecords(){
        if (f == 1){
            c_recordList.add(Record("外卖按时送达",R.drawable.dot,"${c_times}次"))
        }
        else{

        }
    }

    private fun initDRecords(){
        if (f == 1){
            d_recordList.add(Record("外卖延迟送达",R.drawable.dot,"${d_times}次"))
        }
        else{
        }
    }



}