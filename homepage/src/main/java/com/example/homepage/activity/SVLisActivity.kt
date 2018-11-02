package com.example.homepage.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.homepage.R
import kotlinx.android.synthetic.main.activity_svlis.*

/**
 * created by tea9 at 2018/11/1
 * this activity for show scrollView touch listener
 */
class SVLisActivity: AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svlis)
        initLis()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initLis() {
        scroll_view.setOnScrollChangeListener { view, i, i1, i2, i3 ->
            var scrollRect = Rect()
            scroll_view.getHitRect(scrollRect)
            if (whiteLayout.getLocalVisibleRect(scrollRect)) {
                whiteLayout.visibility = View.VISIBLE
                title1.visibility = View.VISIBLE
                title2.visibility = View.GONE
            } else {
                whiteLayout.visibility = View.INVISIBLE
                title1.visibility = View.GONE
                title2.visibility = View.VISIBLE
            }
        }
    }

}