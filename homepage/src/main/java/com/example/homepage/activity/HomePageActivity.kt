package com.example.homepage.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.example.homepage.R
import kotlinx.android.synthetic.main.activity_home_page.*

/**
 * created by tea9 at 2018/8/30
 */
class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }


    fun initTabLayout() {
        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        mTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
            }
        })
    }

}