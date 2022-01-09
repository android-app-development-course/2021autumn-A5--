package com.example.fanlaisu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.example.fanlaisu.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import android.text.TextUtils



//import android.R



@SuppressLint("Range")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    public lateinit var aaa:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
          val account_name = Define.account_name
//        val account_name=intent.getStringExtra("account_name")
//        aaa=account_name.toString()
//        Bmob.initialize(this, "Your Application ID");

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //起监听下面导航栏的作用navigation
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_Local, R.id.navigation_message, R.id.navigation_Personal
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)





//        money.setOnClickListener(){
//            Log.d("okkkkkkkkkkkkkkkkkkk","0000003")
////            val intent= Intent(this,Wallet::class.java)
////            startActivity(intent)
//        }
//        wait.setOnClickListener(){
//            Log.d("okkkkkkkkkkkkkkkkkkk","0000004")
//            val intent= Intent(this,Wait_Takeout::class.java)
//            startActivity(intent)
//        }
//        help.setOnClickListener(){
//            Log.d("okkkkkkkkkkkkkkkkkkk","0000005")
//            val intent= Intent(this,Help_Takeout::class.java)
//            startActivity(intent)
//        }


    }

//    override fun onStart() {
//        super.onStart()
//        val id:Int=intent.getIntExtra("id",2)
//        val help_name=intent.getStringExtra("help_name")
//        if (id==2){
////            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_tag)
////            supportFragmentManager
////                .beginTransaction()
////                .replace(R.id.fragment_container_view_tag, nav_host_fragment_activity_main)
////                .addToBackStack(null)
////                .commit()
//            val intent=Intent(this,nav_host_fragment_activity_main::class.java)
//
//        }
//    }
protected override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    val id = intent.getStringExtra("id")
    if (!TextUtils.isEmpty(id)) {
        if ("2" == id) {
            goFragmentIndex()
        }
    }
}

    private fun goFragmentIndex() {
        val currentIndex=2

    }


}




