package com.example.fanlaisu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.chat.*
import java.lang.Exception
@SuppressLint("Range")
class Chat : AppCompatActivity(),View.OnClickListener {
    private var msgList = ArrayList<Msg>()
    private lateinit var adapter: MsgAdapter
    var duifang="123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        try {
            msgList = ArrayList<Msg>()
            Log.d("come into","222222222222222222")
            val name=intent.getStringExtra("name")//对方的名字
            duifang=name.toString()
            Log.d("from main","22222222222222222222222receive:$name")
//            ok
            supportActionBar?.setTitle(name)
            supportActionBar?.themedContext
            initMsg()
            Log.d("12313okl?","000000000000000000")
//            ok
            val layoutManager = LinearLayoutManager(this)//
            recyclerView.layoutManager = layoutManager
            if (!::adapter.isInitialized) {
                adapter = MsgAdapter(msgList)
            }
            recyclerView.adapter = adapter
            send.setOnClickListener(this)

        }
        catch (e:Exception){
            Log.d("123","sdasdasdasdasda")
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            send -> {
                try {
                    val content = inputText.text.toString()
                    if (content.isNotEmpty()) {
                        val msg = Msg(content, Msg.TYPE_SENT)
                        msgList.add(msg)

                        val dbHelper = MyDatabaseHelper(this, "User.db", 1)
                        val db = dbHelper.writableDatabase
//
                        db.execSQL(
                            "insert into chat_info(name1,name2,info,status) values(?,?,?,?)",
                            arrayOf(Define.account_name,duifang,content,"1")
                        )
                        db.execSQL(
                            "insert into chat_info(name1,name2,info,status) values(?,?,?,?)",
                            arrayOf(duifang,Define.account_name,content,"0")
                        )
//

                        adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecyclerView中的显示
                        recyclerView.scrollToPosition(msgList.size - 1)  // 将 RecyclerView定位到最后一行
                        inputText.setText("") // 清空输入框中的内容
                    }
                }catch (e:Exception){
                    Log.d("wwwwwwwwwwwwwwwwwwwww","caocaocaocaocao")
                }
            }
        }
    }

    private fun initMsg() {
        val msg = Msg("你好.", Msg.TYPE_RECEIVED)
        msgList.add(msg)
//
        Log.d("ooooooooooooooooook","000000000000000")
        val dbHelper = MyDatabaseHelper(this, "User.db", 1)
        Log.d("ooooooooooooooooook","000000000000001")
        val db = dbHelper.writableDatabase
        Log.d("ooooooooooooooooook","000000000000002")
        val cursor = db.query("chat_info", null, null, null, null, null, null)
        Log.d("ooooooooooooooooook","000000000000003")
        Log.d("ooooooooooooooooook","000000000000000000000000000001")
        if (cursor.moveToFirst()) {
            do {
                Log.d("ooooooooooooooooook","000000000000000000000000000002")

                val name1=cursor.getString(cursor.getColumnIndex("name1"))
                val name2=cursor.getString(cursor.getColumnIndex("name2"))
                val info=cursor.getString(cursor.getColumnIndex("info"))
                val status = cursor.getString(cursor.getColumnIndex("status"))
                Log.d("ooooooooooooooooook","000000000000000000000000000003")

                if (name1==Define.account_name&&name2==duifang)
                {
                    Log.d("ooooooooooooooooook","000000000000000000000000000004")

                    if(status=="1"){
                        val msg1 = Msg(info, Msg.TYPE_SENT)
                        msgList.add(msg1)
                    }
                    else if(status=="0")
                    {
                        val msg0 = Msg(info,Msg.TYPE_RECEIVED)
                        msgList.add(msg0)
                    }
                }
            }while (cursor.moveToNext())
        }
        cursor.close()
        Log.d("ooooooooooooooooook","000000000000000000000000000005")

//


//        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
//        msgList.add(msg2)
//        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
//        msgList.add(msg3)
    }
}