package com.bition.constraintlayout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        val intent =  Intent(this,ConstraintLayoutActivity::class.java)
        var arg = "arg"
        when(p0!!.id) {
            R.id.btn1 -> intent.putExtra(arg,1)
            R.id.btn2 -> intent.putExtra(arg,2)
            R.id.btn3 -> intent.putExtra(arg,3)
            R.id.btn4 -> intent.putExtra(arg,4)
            R.id.btn5 -> intent.putExtra(arg,5)
            R.id.btn6 -> intent.putExtra(arg,6)
            R.id.btn_tab -> intent.setClass(this,TabConstraintLayoutActivity::class.java)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn_tab.setOnClickListener(this)
    }
}
