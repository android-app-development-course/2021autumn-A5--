package com.example.fanlaisu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(activity: Activity, val resourceId:Int, data:List<User>):
    ArrayAdapter<User>(activity,resourceId,data) {


    inner class ViewHolder(val userImage:ImageView,val userName:TextView) //内部类，用于对fruitImage和fruitName进行缓存
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val viewHolder:ViewHolder
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val userImage:ImageView = view.findViewById(R.id.userImage)
            val userName:TextView = view.findViewById(R.id.userName)
            viewHolder=ViewHolder(userImage,userName)
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ViewHolder
        }


        val user = getItem(position)

        if(user!=null){
            viewHolder.userImage.setImageResource(user.imageId)
            viewHolder.userName.setText(user.name)
        }
        return view
    }
}