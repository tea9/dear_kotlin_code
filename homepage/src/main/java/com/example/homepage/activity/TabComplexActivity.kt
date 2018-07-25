package com.example.homepage.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.example.homepage.R
import com.example.homepage.adapter.TabComplexViewPagerAdapter
import kotlinx.android.synthetic.main.activity_tab_complex.*
import org.jetbrains.anko.support.v4.onPageChangeListener

/**
 * created by tea9 at 2018/7/20
 */
class TabComplexActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * tab解决方法
     * http://xgfe.github.io/2017/09/17/ivanchou/layout-with-constraintlayout-by-programming/
     *
     * 二级菜单
     * https://blog.csdn.net/shangming150/article/details/80006016
     * **/
    /***
     * rv 多item布局
     * https://blog.csdn.net/zchlww/article/details/51691123
     * https://blog.csdn.net/shangming150/article/details/80006016
     *
     * lv多item
     * https://blog.csdn.net/lplj717/article/details/53420593
     */

    /*
    TODO tab 问题
    TODO rv 多item布局
    * */


    var views = arrayListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_complex)
        tab1.setOnClickListener(this)
        init()
        initViewPager()
    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.tab1 -> checkTab1()
            R.id.tab2 -> checkTab2()
        }
    }


    fun init() {
        tab1.setOnClickListener(this)
        tab2.setOnClickListener(this)

        var view1 = LayoutInflater.from(this).inflate(R.layout.recycler_view,null);
        var view2 = LayoutInflater.from(this).inflate(R.layout.expandable_list_view,null);
        views.add(view1)
        views.add(view2);
    }


    fun initViewPager() {
        var list = arrayListOf("shaomiao","hello","hello","shaimiao","hhh","sss","shaomiao","hello","hello","shaimiao","hhh","sss","shaomiao","hello","hello","shaimiao","hhh","sss","shaomiao","hello","hello","shaimiao","hhh","sss")
        view_pager.adapter = TabComplexViewPagerAdapter(views,this,list,list)
        view_pager.onPageChangeListener {
            onPageSelected { i->
                when(i){
                    0->{
                       //修改tab
                    }
                    1->{

                    }
                }
            }
        }
    }

    fun checkTab1() {
        view_pager.currentItem = 0
    }

    fun checkTab2() {
        view_pager.currentItem = 1
    }

}