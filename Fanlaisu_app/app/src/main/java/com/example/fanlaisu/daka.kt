package com.example.fanlaisu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import kotlinx.android.synthetic.main.daka.*

class daka : AppCompatActivity() {
    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daka)

        supportActionBar?.hide()
        btn.setOnClickListener {

            btn.mBtnAinim()//开始加载动画
            Thread(Runnable {
                Thread.sleep(2000)
                Looper.prepare()
                if (flag == true) {
                    runOnUiThread({
                        btn.endAnim("打卡成功！")
                    })
                    flag = false
                } else {
                    runOnUiThread({
                        btn.endAnim("已经打卡过啦！")
                    })
                }
                Looper.loop()
            }).start()
        }
    }
}