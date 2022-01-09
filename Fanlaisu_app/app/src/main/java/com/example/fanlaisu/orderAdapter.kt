package com.example.fanlaisu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class orderAdapter(activity: Activity, val resourceId:Int, data:List<Order>):
    ArrayAdapter<Order>(activity,resourceId,data) {
        override fun getView(position:Int, convertView: View?, parent:ViewGroup):View{
            val view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val orderImage:ImageView = view.findViewById(R.id.order_state_image)
            val orderInfo: TextView = view.findViewById(R.id.order_information)
            val orderState:TextView = view.findViewById(R.id.order_state)
            val order = getItem(position)
            if(order!=null){
                orderImage.setImageResource(order.imagedId)
                orderInfo.text = order.info
                orderState.text = order.state
            }
            return view
        }
}