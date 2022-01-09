package com.example.fanlaisu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class recordAdapter(activity: Activity, val resourceId:Int, data:List<Record>):
    ArrayAdapter<Record>(activity,resourceId,data) {
    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View{
        val view = LayoutInflater.from(context).inflate(resourceId,parent,false)
        val recordImage:ImageView = view.findViewById(R.id.record_image)
        val recordInfo: TextView = view.findViewById(R.id.record_information)
        val recordTimes: TextView = view.findViewById(R.id.record_times)
        val record = getItem(position)
        if(record!=null){
            recordImage.setImageResource(record.imagedId)
            recordInfo.text = record.info
            recordTimes.text = record.times
        }
        return view
    }
}