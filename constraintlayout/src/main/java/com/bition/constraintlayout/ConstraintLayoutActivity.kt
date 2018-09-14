package com.bition.constraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * created by tea9 at 2018/9/14
 */
class ConstraintLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arg = intent.getIntExtra("arg", 0)
        when (arg) {
            1 -> setContentView(R.layout.constraintlayout1)
            2 -> setContentView(R.layout.constraintlayout2)
            3 -> setContentView(R.layout.constraintlayout3)
            4 -> setContentView(R.layout.constraintlayout4)
            5 -> setContentView(R.layout.constraintlayout5)
            6 -> setContentView(R.layout.constraintlayout6)
            else -> setContentView(R.layout.constraintlayout1)
        }
    }

}