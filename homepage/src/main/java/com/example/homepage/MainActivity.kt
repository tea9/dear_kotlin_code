package com.example.homepage

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.homepage.activity.HomeActivity
import com.example.homepage.activity.HomeViewPagerActivity
import com.example.homepage.activity.TabComplexActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

    }

    class MainActivityUI:AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                padding = dip(10)
                button(R.string.btn1) {
                    backgroundColor = R.color.colorPrimaryDark
                    textColor = Color.WHITE
                    onClick { startActivity<HomeViewPagerActivity>() }
                }
                button(R.string.btn2) {
                    backgroundColor = R.color.colorPrimaryDark
                    textColor = Color.WHITE
                    onClick {  startActivity<HomeActivity>() }
                }
                button(R.string.btn3){
                    backgroundColor = R.color.colorPrimaryDark
                    textColor = Color.WHITE
                    onClick { startActivity<TabComplexActivity>() }
                }
            }
        }

    }

}
