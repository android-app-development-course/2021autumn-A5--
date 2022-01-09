package com.example.fanlaisu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class nameAdapter(activity: Activity, val resourceId:Int, data:List<nameInfo>):
    ArrayAdapter<nameInfo>(activity,resourceId,data) {
    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View{
        val view = LayoutInflater.from(context).inflate(resourceId,parent,false)
        val nameInformation:TextView = view.findViewById(R.id.name_Info)
        val info = getItem(position)
        if(info!=null){
            nameInformation.text = info.username
        }
        return view
    }
}