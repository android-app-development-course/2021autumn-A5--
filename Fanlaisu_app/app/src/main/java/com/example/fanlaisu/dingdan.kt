package com.example.fanlaisu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.fragment_local.*
@SuppressLint("Range")

class dingdan : AppCompatActivity() {
//    private val data = listOf("取餐地点：南门\n送餐地点：东12\n完成时间：2021/11/29","取餐地点：南门\n" +
//            "送餐地点：东12\n" +
//            "完成时间：2021/11/30/","取餐地点：南门\n" +
//            "送餐地点：东12\n" +
//            "完成时间：2021/12/1","取餐地点：南门\n" +
//            "送餐地点：东12\n" +
//            "完成时间：2021/12/2")
//    private val data2 = listOf("取餐地点：南门\n送餐地点：东12\n预计送达时间：12:10")
    var f = 1
    private val orderList = ArrayList<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dingdan)
        supportActionBar?.hide()

        val orderListView:ListView = findViewById(R.id.order_listView)
        initOrders()
        val adapter = orderAdapter(this,R.layout.list_item_dingdan, orderList)
        orderListView.adapter = adapter

//        var b_ing: Button = findViewById(R.id.button2)
//        var listView: ListView = findViewById(R.id.listView)
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
//        listView.adapter = adapter


//        var b_all: Button = findViewById(R.id.button1)
//        b_all.setOnClickListener {
//            //val intent = Intent(this,dingdan::class.java)
//            //startActivity(intent)
//            listView.adapter = adapter
//        }

//        b_ing.setOnClickListener {
////            val intent = Intent(this,dingdan2::class.java)
////            startActivity(intent)
//            val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data2)
//            listView.adapter = adapter2
//        }
    }
    private fun initOrders(){
//        repeat(5){
//            if (f==1){
//                orderList.add(Order("订单1",R.drawable.doing,"进行中"))
//            }
//            else{
//                orderList.add(Order("订单2",R.drawable.done,"已完成"))
//            }
//            f = f*(-1)
//        }
        val account_name=Define.account_name
        val dbHelper = MyDatabaseHelper(this,"User.db", 1)
        val db = dbHelper.readableDatabase
        val cursor = db.query("dingdan",null,null,null,null,null,null)
        var count=0


        if(cursor.moveToFirst()){
            do {
                Log.d("okkkkkkkkkkkkkkkkkkk","0000000000000000000000003")
                val name1=cursor.getString(cursor.getColumnIndex("name1"))
                val name2=cursor.getString(cursor.getColumnIndex("name2"))
                val start1=cursor.getString(cursor.getColumnIndex("start1"))
                val where1=cursor.getString(cursor.getColumnIndex("where1"))
                val status = cursor.getString(cursor.getColumnIndex("status"))
                Log.d("okkkkkkkkkkkkkkkkkkk","0000000000000000000000005")
                if(status=="0"){
                    if (account_name==name1){
                        count=1
                        orderList.add(Order("等待"+name2+"代取外卖",R.drawable.doing,"进行中"))
                    }
                    else if (account_name==name2){
                        count=2
                        orderList.add(Order("帮"+name1+"代取",R.drawable.doing,"进行中"))
                    }

                }
                else{
                    if (account_name==name1){
                        count=1
                        orderList.add(Order("等待"+name2+"代取外卖",R.drawable.done,"已完成"))
                    }
                    else if (account_name==name2){
                        count=2
                        orderList.add(Order("帮"+name1+"代取",R.drawable.done,"已完成"))
                    }
                }

            }while (cursor.moveToNext())

        }
        cursor.close()



    }
}