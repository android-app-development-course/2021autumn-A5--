package com.example.fanlaisu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.example.fanlaisu.Login
import kotlinx.android.synthetic.main.login.*
@SuppressLint("Range")
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()














        Log.d("123123123","123123123")
        val  dbHelper = MyDatabaseHelper(this,"User.db",1)
        val db=dbHelper.readableDatabase
        val cursor=db.query("User",null,null,null,null,null,null)
        if(cursor.moveToFirst()) {
            do {
                val account_db = cursor.getString(cursor.getColumnIndex("account"))
                val password_db = cursor.getString(cursor.getColumnIndex("password"))
                LaccountEdit.text = Editable.Factory.getInstance().newEditable(account_db)
                LpasswordEdit.text = Editable.Factory.getInstance().newEditable(password_db)
                break
            } while (cursor.moveToNext())
        }
        try {
            Register.setOnClickListener{
                Log.d("123123123","123123124")
                dbHelper.writableDatabase   //第一次则生成一个数据库
                val intent=Intent(this,com.example.fanlaisu.Register::class.java)
                startActivity(intent);
            }
        }catch (e:Exception){
            Log.d("asdasdasdasd","进不去")
        }


        var count=0
        Login.setOnClickListener(){
            try{
                var account_content=LaccountEdit.text.toString()
                var password_content=LpasswordEdit.text.toString()
                if(account_content.length==0||password_content.length==0){
                    Toast.makeText(this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show()
                }
                else{
                    val cursor=db.query("User",null,null,null,null,null,null)
                    if(cursor.moveToFirst()){
                        do{
                            val account_db=cursor.getString(cursor.getColumnIndex("account"))
                            val password_db=cursor.getString(cursor.getColumnIndex("password"))
                            if((account_content==account_db) &&  (password_content==password_db))
                            {
                                count=1
                                Define.account_name=account_content
                                val intent=Intent(this,MainActivity::class.java)
                                intent.putExtra("account_name",account_content)
                                startActivity(intent)
                            }
                        }while (cursor.moveToNext())
                        if(count==0)
                        {

                            Log.d("1231456",count.toString())
                            Toast.makeText(this,"用户名或者密码错误\n请重新输入",Toast.LENGTH_SHORT).show()
                            LaccountEdit.text.clear()
                            LpasswordEdit.text.clear()
                        }


                    }
                    else{

                        Toast.makeText(this, "请先注册", Toast.LENGTH_SHORT).show()
                        if(count==0){
//                    finish()
                        }
                    }
                }
            } catch (e: Exception){
                Log.d("app321","sssssaaasdsadasda")
            }
        }
    }
    companion object  {
        @JvmStatic
        val REQUEST_CODE_PERMISSION_CHOOSE_PHOTO = 1
        @JvmStatic
        val REQUEST_CODE_PERMISSION_TAKE_PHOTO = 2
        @JvmStatic
        val REQUEST_CODE_CHOOSE_PHOTO = 1
        @JvmStatic
        val REQUEST_CODE_TAKE_PHOTO = 2
        @JvmStatic
        val REQUEST_CODE_CROP = 3
        @JvmStatic
        val REQUEST_NICK = 0x123
    }
}