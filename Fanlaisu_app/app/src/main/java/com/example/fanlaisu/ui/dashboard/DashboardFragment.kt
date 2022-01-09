package com.example.fanlaisu.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fanlaisu.*
import com.example.fanlaisu.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*
@SuppressLint("Range")

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    private var userList=ArrayList<User>()
    private  var count=0

    override fun onStart() {
        super.onStart()
        userList=ArrayList<User>()
        initFruits()
        val adapter = UserAdapter(requireActivity(),R.layout.list_item,userList)
        listView.adapter=adapter
        listView.setOnItemClickListener{parent,view,position,id->
            val user=userList[position]
            val data=user.name

            val intent = Intent(requireActivity(),Chat::class.java)
            Log.d("chatting","111111111111111111111111111111111send:$data")
            intent.putExtra("name",data)
            startActivity(intent)

        }

    }


    private fun initFruits(){

//        if (count==0){
//            userList.add(User("Word麻鸭",R.drawable.pic1))
//            userList.add(User("我睡觉的时候不困",R.drawable.pic2))
//            userList.add(User("Eliauk",R.drawable.pic3))
//            userList.add(User("Augenstern",R.drawable.pic4))
//            userList.add(User("Kotlin",R.drawable.pic5))
//            userList.add(User("马可波罗炒饭",R.drawable.pic6))
//            count=1
//        }

        val dbHelper = MyDatabaseHelper(requireContext(), "User.db", 1)
        val db = dbHelper.readableDatabase
        val cursor = db.query("dingdan", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val name1 = cursor.getString(cursor.getColumnIndex("name1"))
                val name2=cursor.getString(cursor.getColumnIndex("name2"))
                val start1=cursor.getString(cursor.getColumnIndex("start1"))
                val where1=cursor.getString(cursor.getColumnIndex("where1"))
                val status = cursor.getString(cursor.getColumnIndex("status"))

                val randoms = (1..6).random()
                var a=R.drawable.pic1
                var b=R.drawable.pic2
                var c=R.drawable.pic3
                var d=R.drawable.pic4
                var e=R.drawable.pic5
                var f=R.drawable.pic6
                val list= listOf<Int>(a,b,c,d,e,f)
                if(status=="0"){
                    if (name1==Define.account_name){

                        userList.add(User(name2,list[randoms-1]))
                    }
                    else if (name2==Define.account_name){
                        userList.add(User(name1,list[randoms-1]))
                    }
                }

            }while (cursor.moveToNext())
        }
        cursor.close()


    }
}