package com.example.homepage.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homepage.R
import com.example.homepage.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_banner.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

/**
 * created by tea9 at 2018/7/10
 */
class HomeActivity :AppCompatActivity() {

    var tabList = arrayListOf("标题1","标题2","tab3dddd","tab45555","tabdddd5","tabkkkk6","tabssdsd7")
    var imgList:ArrayList<String> = arrayListOf("https://images.unsplash.com/photo-1531026383433-6ed5a112afbc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=c010c700aac502636ad0b579ce1274a4&auto=format&fit=crop&w=1650&q=80","https://images.unsplash.com/photo-1531075515553-b4d1f75ff534?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b3f6b409e70fca36a74369d882e85f49&auto=format&fit=crop&w=1567&q=80","https://images.unsplash.com/photo-1531130744926-1d86103aebeb?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=28f240aae3de685fc4742f09c922f6f8&auto=format&fit=crop&w=1714&q=80")
    var mesList = arrayListOf("您的公告1","您的公告2","您的公告3")
    var fragmentList:ArrayList<Fragment>? = ArrayList()
    var fragmentContainerHelper = FragmentContainerHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        initBanner()
        initViewFlipper()
        initFragments()
        initMagicIndicator()

        switchPages(1)

    }

    fun initBanner() {
        banner.setAdapter(BGABanner.Adapter<ImageView,String> { banner, itemView, model, position ->
            Glide.with(itemView.context)
                    .load(model)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate().centerCrop())
                    .into(itemView)
        })
        banner.setData(imgList, Arrays.asList("","",""))
        // 点击事件
        banner.setDelegate { banner, itemView, model, position -> toast(model.toString()+position) }
    }

    fun initViewFlipper() {
        for (i in mesList.indices) {
            var view = LayoutInflater.from(this).inflate(R.layout.item_textview,null)
            view.findViewById<TextView>(R.id.text_view).text = mesList[i]
            view.setOnClickListener {
                toast(mesList[i]+i)
            }
            view_flipper.addView(view)
        }
        view_flipper.isAutoStart = true
    }

    fun initFragments() {
        for(i in tabList.indices) {
            var fragment = HomeFragment.create("shaomiaohello"+i)
            fragmentList!!.add(fragment)
        }
    }

    fun initMagicIndicator() {
        var commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return  if (tabList == null)0 else tabList.size
            }

            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                var colorTransitionPagerTitleView = ColorTransitionPagerTitleView(p0)
                colorTransitionPagerTitleView.normalColor= ContextCompat.getColor(p0!!,R.color.abc_btn_colored_borderless_text_material)
                colorTransitionPagerTitleView.selectedColor = ContextCompat.getColor(p0!!,R.color.abc_btn_colored_borderless_text_material)
                colorTransitionPagerTitleView.text = tabList[p1].toUpperCase()
                colorTransitionPagerTitleView.textSize = 15f

                colorTransitionPagerTitleView.setOnClickListener (View.OnClickListener {
                    //                    view_pager.currentItem = p1
                    fragmentContainerHelper.handlePageSelected(p1);
                    switchPages(p1)

                })
//                colorTransitionPagerTitleView.setOnClickListener(View.OnClickListener {
//                    commonNavigator.onPageSelected(p1)
//                    commonNavigator.onPageScrolled(p1, 0f, 0)
//                })

                return colorTransitionPagerTitleView
            }
            override fun getIndicator(p0: Context?): IPagerIndicator {
                var indicator: LinePagerIndicator = LinePagerIndicator(p0)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.setColors(ContextCompat.getColor(p0!!,R.color.abc_search_url_text ))
//                indicator.lineHeight = 100f
//                indicator.yOffset = -5f
//                indicator.x = 0.0f
                indicator.y = -5f
                indicator.lineHeight = 15f
                return indicator

            }
        }
        magic_indicator.navigator = commonNavigator
        fragmentContainerHelper.attachMagicIndicator(magic_indicator)

    }

    fun switchPages(index:Int) {
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        var fragment:Fragment? = null
        var i =0
        var j = fragmentList!!.size
        while(i<j) {
            if (i == index) {
                i++
                continue;
            }
            fragment = fragmentList!!.get(i)
            if (fragment.isAdded) {
                fragmentTransaction.hide(fragment)
            }
            i++
        }

        fragment = fragmentList!!.get(index)
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
        } else {
            fragmentTransaction.add(R.id.ll_content,fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()

    }


}