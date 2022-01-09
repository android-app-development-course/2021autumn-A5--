package com.example.fanlaisu
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.register.*
@SuppressLint("Range")
class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("hnhjjk","123123132123")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        Log.d("dfsdfsfds","123123132123")

        supportActionBar?.hide()

        try {
            val  dbHelper = MyDatabaseHelper(this,"User.db",1)
            val db=dbHelper.writableDatabase

            register.setOnClickListener(){
                var count=0
                if(RpasswordEdit.text.length<8) {
                    AlertDialog.Builder(this).apply {
                        setTitle("温馨提示！")
                        setMessage("密码不能少于8位")
                        setCancelable(false)
                        setPositiveButton("ok") { dialog, which ->
                            Log.d("okkkkkkkkkkkkkkkkkkk", "00000010")
                        }
                        show()

                    }
                }
                else if (RaccountEdit.text.length==0||RID.text.length==0||RagainpasswordEdit.text.length==0||RPhone.text.length==0)
                {
                    AlertDialog.Builder(this).apply {
//                        setTitle("温馨提示！")
                        setMessage("请完善信息")
                        setCancelable(false)
                        setPositiveButton("ok") { dialog, which ->
                            Log.d("okkkkkkkkkkkkkkkkkkk", "00000010")
                        }
                        show()

                    }
                }
//                else if(RpasswordEdit.text!= RagainpasswordEdit.text){
//                    AlertDialog.Builder(this).apply {
////                        setTitle("温馨提示！")
//                        setMessage("再次确认密码不正确")
//                        setCancelable(false)
//                        setPositiveButton("ok") { dialog, which ->
//                            Log.d("okkkkkkkkkkkkkkkkkkk", "00000010")
//                        }
//
//                        show()
//
//                    }
//                }
                else{


                    val cursor = db.query("User", null, null, null, null, null, null)
                    if (cursor.moveToFirst()){
                        do {
                            val accountdb=cursor.getString(cursor.getColumnIndex("account"))
                            if (RaccountEdit.text.toString()==accountdb)
                            {
                                AlertDialog.Builder(this).apply {
                                    setTitle("温馨提示！")
                                    setMessage("用户名"+accountdb+"已有其他用户使用\n"+"请重新命名")
                                    setCancelable(false)
                                    count=1
                                    setPositiveButton("ok") { dialog, which ->

                                        Log.d("okkkkkkkkkkkkkkkkkkk","00000010")


                                    }
                                    show()
                                }
                                break

                            }
                        }while (cursor.moveToNext())
                    }

                    if (count==0){
                        db.execSQL("insert into User(account,password)values(?,?)",
                            arrayOf(RaccountEdit.text.toString(),RpasswordEdit.text.toString())
                        )
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"Register_succeed",Toast.LENGTH_SHORT).show()
//                finish()
                    }


                }




            }
        }catch (e:Exception){
            Log.d("sdasdasd","231231231232321")
        }

    }
}



