package com.example.fanlaisu.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fanlaisu.*
import com.example.fanlaisu.databinding.FragmentHomeBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//创建调用
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }




//摧毁
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        money.setOnClickListener(){
            Log.d("okkkkkkkkkkkkkkkkkkk","0000003")



            val intent= Intent(requireActivity(), Wallet::class.java)
            startActivity(intent)
        }
        wait.setOnClickListener(){Log.d("okkkkkkkkkkkkkkkkkkk","0000004")

            val intent= Intent(requireActivity(), Wait_Takeout::class.java)
            startActivity(intent)
        }
        help.setOnClickListener(){
            Log.d("okkkkkkkkkkkkkkkkkkk","000000help")
            val intent= Intent(requireActivity(), Help_Takeout::class.java)
            startActivity(intent)
        }
        icon_qr_code.setOnClickListener(){
            Log.d("okkkkkkkkkkkkkkkkkkk","000000wait")
            val intent= Intent(requireActivity(), Qr_code::class.java)
            startActivity(intent)
        }
        clock_in.setOnClickListener(){
            val intent= Intent(requireActivity(), daka::class.java)
            startActivity(intent)
        }





        scanning.setOnClickListener(){


            var intentIntegrator = IntentIntegrator(requireActivity())

            intentIntegrator= IntentIntegrator.forSupportFragment(requireParentFragment())

            //  1.扫描成功后的提示音，默认关闭
            intentIntegrator.setBeepEnabled(true)

            //  2.启动后置摄像头扫描，若为 1 为前置摄像头，默认后置
            intentIntegrator.setCameraId(0)

            /*  3.设置扫描的条码的格式:默认为所有类型
             *   IntentIntegrator.PRODUCT_CODE_TYPES:商品码类型
             *   IntentIntegrator.ONE_D_CODE_TYPES:一维码类型
             *   IntentIntegrator.QR_CODE:二维码
             *   IntentIntegrator.DATA_MATRIX:数据矩阵类型
             *   IntentIntegrator.ALL_CODE_TYPES:所类有型
             * */intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)

            /*  4.方向锁：true为锁定，false反之，默认锁定.
            ps:在AndroidManifest.xml里设置以下属性，则扫码界面完全依赖传感器（tools红色提示，指向它会提示，点击左边蓝色Create...即可）
            <activity
                android:name="com.journeyapps.barcodescanner.CaptureActivity"
                android:screenOrientation="fullSensor"
                tools:replace="screenOrientation" />
            * */intentIntegrator.setOrientationLocked(true)

            //  5.设置扫描界面的提示信息：默认为:请将条码置于取景框内扫描。(ps：设置没提示文字：setPrompt(""))
            intentIntegrator.setPrompt("请扫描二维码")

            //  6.设置关闭扫描的时间(单位：毫秒)，不设置不关闭
            intentIntegrator.setTimeout(60000)

            //  7.保存二维码图片:在onActivityResult方法里可获取保存的路径，根据需要来是否需要保存
            intentIntegrator.setBarcodeImageEnabled(true)

            //竖屏
            intentIntegrator.setCaptureActivity(ScanActivity::class.java)

            //启动扫描
            intentIntegrator.initiateScan()
        }


    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {

            //==是否扫到内容
            if (result.getContents() != null) {
                Toast.makeText(requireContext(), "扫描结果：" + result.getContents(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "取消扫码", Toast.LENGTH_LONG).show()
            }

//                //==是否有保存照片的路径  在intentIntegrator已设置保存照片
//                if (result.getBarcodeImagePath() != null) {
//                    var file: FileInputStream? = null
//                    try {
//                        file = FileInputStream(File(result.getBarcodeImagePath()))
//                        var ivImage: ImageView = findViewById(R.id.iv_image)
//                        ivImage.setImageBitmap(BitmapFactory.decodeStream(file)) //显示获取的照片
//                    } catch (e: FileNotFoundException) {
//                        e.printStackTrace()
//                    } finally {
//                        try {
//                            if (file != null) {
//                                file.close()
//                            }
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                    }
//                }

            /*  获取条码种类：在intentIntegrator.setDesiredBarcodeFormats那设置扫码格式后（点击格式可进入查看该格式有多少个类型）

                例如：PRODUCT_CODE_TYPES：商品码类型，它就有 UPC_A, UPC_E, EAN_8, EAN_13, RSS_14 种类
                public static final Collection<String> PRODUCT_CODE_TYPES = list(UPC_A, UPC_E, EAN_8, EAN_13, RSS_14);

                根据getFormatName获取到的种类，就知道是哪个扫码格式，进而根据需求进行相关操作
             */

//            if (result.getFormatName() != null) {
//                Toast.makeText(this, "图片格式：" + result.getFormatName(), Toast.LENGTH_LONG).show()
//            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }




}



