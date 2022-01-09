package com.example.fanlaisu

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.qr_code.*

class Qr_code : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code)
        supportActionBar?.hide()

        val dbHelper = MyDatabaseHelper(this, "User.db", 1)
        val db = dbHelper.writableDatabase
        val cursor = db.query("dingdan", null, null, null, null, null, null)

        if(cursor.moveToFirst()){
            do {







            }while (cursor.moveToNext())
        }
        cursor.close()













        val bitmap:Bitmap=getQrCodeBitmap("fanlaisu",Define.account_name)
        qr_code.setImageBitmap(bitmap)



    }


    private fun getQrCodeBitmap(ssid: String, password: String): Bitmap {
        val qrCodeContent = "platform:$ssid;\nuser:$password;;"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hints)

        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}