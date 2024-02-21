package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    val textView=findViewById<TextView>(R.id.text)
    val button=findViewById<Button>(R.id.btn_scanner)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this@MainActivity)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setPrompt("Scan a QR Code")
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            intentIntegrator.initiateScan()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(intentResult!=null){
            val contents = intentResult.contents
            if(contents!=null){
                textView.setText(intentResult.contents)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}