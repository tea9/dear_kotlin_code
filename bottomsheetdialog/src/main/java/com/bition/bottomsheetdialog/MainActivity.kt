package com.bition.bottomsheetdialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1.setOnClickListener{
            /**
             * dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  //←重点在这里，来，都记下笔记
             */

//            var dialog = BottomSheetDialog(this)
//            var view = LayoutInflater.from(this).inflate(R.layout.text,null)
//            dialog.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            dialog.setContentView(view)
//            dialog.setCancelable(true)
//            dialog.setCanceledOnTouchOutside(true)
//            dialog.show()

        }
    }
}
