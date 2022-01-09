package com.example.fanlaisu

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class  MyDatabaseHelper(val context: Context, name:String, version:Int):

    SQLiteOpenHelper(context,name,null,version){

    private  val  createUser="create table User("+
            "id integer primary key autoincrement,"+
            "account text,"+
            "password text)"

    private val  createWait="create table Wait("+
            "name1 text primary key,"+
            "start1 text,"+
            "where1 text)"


    private val  createHelp="create table Help("+
            "name2 text primary key,"+
            "start2 text,"+
            "where2 text)"

    private val  createdingdan="create table dingdan("+
            "id integer primary key autoincrement,"+
            "name1 text,"+
            "name2 text,"+
            "start1 text,"+
            "where1 text,"+
            "status text)"

    private  val  chat="create table chat_info("+
            "id integer primary key autoincrement,"+
            "name1 text,"+
            "name2 text,"+
            "info text,"+
            "status text)"




    override fun onCreate(db: SQLiteDatabase){
        Log.d("okkkkkkkkkkkkkkkkkkk","0000000")
        db.execSQL(createUser)
        db.execSQL(createWait)
        db.execSQL(createHelp)
        db.execSQL(createdingdan)
        db.execSQL(chat)
        db.execSQL(
            "insert into Help(name2 , start2 , where2 ) values(?,?,?)",
            arrayOf("小志","西门","西区")
        )
        db.execSQL(
                "insert into Help(name2 , start2 , where2 ) values(?,?,?)",
        arrayOf("小敏","南门","东区")
        )
        db.execSQL(
            "insert into Wait(name1 , start1 , where1 ) values(?,?,?)",
            arrayOf("婕哥","南门","东区")
        )
        Log.d("okkkkkkkkkkkkkkkkkkk","0000001")

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int){
        Log.d("okkkkkkkkkkkkkkkkkkk","0000002")
        if (oldVersion<=1)
        {
            Log.d("okkkkkkkkkkkkkkkkkkk","0000003")

            Log.d("okkkkkkkkkkkkkkkkkkk","0000004")
//            db.execSQL(createWait)
//            db.execSQL(createHelp)
//            db.execSQL(createdingdan)
//
//            db.execSQL(
//                "insert into Help(name2 , start2 , where2 ) values(?,?,?)",
//                arrayOf("小志","西门","西区")
//            )
//            db.execSQL(
//                "insert into Help(name2 , start2 , where2 ) values(?,?,?)",
//                arrayOf("小敏","南门","东区")
//            )

        }
    }

}