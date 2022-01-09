package com.example.fanlaisu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.fanlaisu.ui.mylocal.MylocalFragment
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.wait_takeout.*
@SuppressLint("Range")
class Wait_Takeout : AppCompatActivity() {
//    val account_name = intent.getStringExtra("account_name")
    val account_name=Define.account_name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wait_takeout)
        supportActionBar?.hide()

        Log.d("okkkkkkkkkkkkkkkkkkk","0000005")
        val dbHelper = MyDatabaseHelper(this, "User.db", 1)
        val db = dbHelper.writableDatabase
        out_publish.setOnClickListener {
//数据插入Wait表中
            Log.d("okkkkkkkkkkkkkkkkkkk","0000006")

//            db.execSQL(
//                "insert into Wait(name1 , start1 , where1 ) values(?,?,?)",
//                arrayOf(
//                    account_name.toString(),
//                    wait_start.text.toString(),
//                    wait_where.text.toString()
//                )
//            )
//            判断输入是否为空
            if(wait_start.length()==0 ||wait_where.length()==0||wait_phone.length()==0||wait_name.length()==0){
                AlertDialog.Builder(this).apply {
                    setTitle("温馨提示！")
                    setMessage("外卖信息不足！\n请补充完整")
                    setCancelable(false)
                    setPositiveButton("ok") { dialog, which ->

                    }
                    show()
                }
            }
            else{


                Log.d("okkkkkkkkkkkkkkkkkkk","0000007")
                var tiaozhaun=0
                var count=0
                var count2=0
//            搜索Help表
                val cursor = db.query("Help", null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    do {
                        val name2 = cursor.getString(cursor.getColumnIndex("name2"))
                        val start2 = cursor.getString(cursor.getColumnIndex("start2"))
                        val where2 = cursor.getString(cursor.getColumnIndex("where2"))
                        Log.d("okkkkkkkkkkkkkkkkkkk","000000888")
                        if ((start2 == wait_start.text.toString()) && (where2 == wait_where.text.toString())&&account_name!=name2) {
//                            val cursor2 = db.query("dingdan", null, null, null, null, null, null)
//                            if (cursor2.moveToFirst()){
//                                do {
//                                    val dingdan_name2=cursor2.getString(cursor2.getColumnIndex("name2"))
//                                    val dingdan_status=cursor2.getString(cursor2.getColumnIndex("status"))
//                                    if(!(name2==dingdan_name2&&dingdan_status=="0")){
//                                        count2=1
//                                        break
//                                    }
//
//
//                                }while (cursor2.moveToNext())
//                                if (count2!=1){
//                                    break
//                                }
//                            }
//                            cursor2.close()
//
//

                            db.execSQL(
                                "insert into dingdan(name1,name2,start1,where1,status ) values(?,?,?,?,?)",
                                arrayOf(
                                    account_name.toString(),
                                    name2.toString(),
                                    wait_start.text.toString(),
                                    wait_where.text.toString(),
                                    "0"
                                )
                            )
                            count=1
                            Log.d("okkkkkkkkkkkkkkkkkkk","0000009")
                            AlertDialog.Builder(this).apply {
                                setTitle("恭喜你！")
                                setMessage("用户" + name2 + "接受了您的订单啦")
                                setCancelable(false)
                                setPositiveButton("ok") { dialog, which ->
//                                Log.d("okkkkkkkkkkkkkkkkkkk","00000010")
//                                val intent = Intent(parent, MainActivity::class.java)
//                                intent.putExtra("help_name",name2)
//
//                                intent.putExtra("id","2")
//                                startActivity(intent)
                                }
                                show()
                            }
//                        ①、订单页要加上此订单+进行中
//                        ②、地图下加上此订单信息,检查订单数据库，看是否有进行中的订
//                        finish()
                        }
                    } while (cursor.moveToNext())
                }
                cursor.close()
                if(count==0){
                    AlertDialog.Builder(this).apply {
                        setTitle("抱歉")
                        setMessage("目前无匹配用户可帮你代取外卖\n我们将赠送少量吃货币作为补偿！")
                        setCancelable(false)
                        setPositiveButton("ok") { dialog, which ->
                        }
                        show()
                    }
                }

            }






        }
    }
}