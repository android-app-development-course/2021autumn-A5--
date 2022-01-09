package com.example.fanlaisu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class faceInfo(val imagedId:Int)
class nameInfo(val username:String)
class xiugai : AppCompatActivity() {
    var f = 1
    var somename = "李华"
    private val faceList = ArrayList<faceInfo>()
    private val nameList = ArrayList<nameInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xiugai)
//        val flist: ListView = findViewById(R.id.face_list)
//        val nlist: ListView = findViewById(R.id.name_list)

//        var b_pic: Button = findViewById(R.id.face)
//        var b_name: Button = findViewById(R.id.userName)

//        b_name.setOnClickListener {
//            val intent = Intent(this,name::class.java)
//            startActivity(intent)
//        }
        supportActionBar?.hide()
        initFace()
        val faceListView: ListView = findViewById(R.id.face_list)
        val face_adapter = faceAdapter(this,R.layout.face_list_item, faceList)
        faceListView.adapter = face_adapter

        faceListView.setOnItemClickListener { parent, view, posistion, id ->
            val intent = Intent(this,face::class.java)
            startActivity(intent)
        }

        initName()
        val nameListView: ListView = findViewById(R.id.name_list)
        val name_adpter = nameAdapter(this,R.layout.name_list_item,nameList)
        nameListView.adapter = name_adpter

        nameListView.setOnItemClickListener { parent, view, posistion, id ->
            val intent = Intent(this,name::class.java)
            startActivity(intent)
        }
    }

    private fun initFace(){
        if (f == 1){
            faceList.add(faceInfo(R.drawable.touxiang2))
        }
        else{

        }
    }

    private fun initName(){
        if (f == 1){
            nameList.add(nameInfo(somename))
        }
        else{

        }
    }


}

