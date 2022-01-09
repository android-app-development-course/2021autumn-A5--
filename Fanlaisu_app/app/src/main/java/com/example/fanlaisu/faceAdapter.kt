package com.example.fanlaisu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class faceAdapter(activity: Activity, val resourceId:Int, data:List<faceInfo>):
    ArrayAdapter<faceInfo>(activity,resourceId,data) {
    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View{
        val view = LayoutInflater.from(context).inflate(resourceId,parent,false)
        val faceImage:ImageView = view.findViewById(R.id.face_image)
        val info = getItem(position)
        if(info!=null){
            faceImage.setImageResource(info.imagedId)
        }
        return view
    }
}