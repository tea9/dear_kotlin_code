package com.bition.constraintlayout

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_tab_constraintlayout.*

/**
 * created by tea9 at 2018/9/14
 */
class TabConstraintLayoutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tx1 -> vp1.currentItem = 0
            R.id.tx2 -> vp1.currentItem = 1
            R.id.tx3 -> vp1.currentItem = 2
            R.id.tx4 -> vp1.currentItem = 3
            R.id.tx5 -> vp1.currentItem = 4
        }
    }

    var views = arrayListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_constraintlayout)
        initView()
        initViewPager()
        clearView()
        clickView(1)
    }

    private fun initView() {
        tx1.setOnClickListener(this)
        tx2.setOnClickListener(this)
        tx3.setOnClickListener(this)
        tx4.setOnClickListener(this)
        tx5.setOnClickListener(this)
        val v1 = LayoutInflater.from(this).inflate(R.layout.item_text, null)
        val v2 = LayoutInflater.from(this).inflate(R.layout.item_text, null)
        val v3 = LayoutInflater.from(this).inflate(R.layout.item_text, null)
        val v4 = LayoutInflater.from(this).inflate(R.layout.item_text, null)
        val v5 = LayoutInflater.from(this).inflate(R.layout.item_text, null)
        views.addAll(arrayListOf<View>(v1, v2, v3, v4, v5))
    }

    private fun initViewPager() {
        vp1.adapter = ViewPagerAdapter(views)
        vp1.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> clickView(1)
                    1 -> clickView(2)
                    2 -> clickView(3)
                    3 -> clickView(4)
                    4 -> clickView(5)
                }
            }
        })
        vp1.currentItem = 0
    }

    private fun clearView() {
        val arg = ContextCompat.getColor(this, R.color.gray_deep)
        tx1.setTextColor(arg)
        tx2.setTextColor(arg)
        tx3.setTextColor(arg)
        tx4.setTextColor(arg)
        tx5.setTextColor(arg)
        view1.visibility = View.GONE
        view2.visibility = View.GONE
        view3.visibility = View.GONE
        view4.visibility = View.GONE
        view5.visibility = View.GONE

    }

    private fun clickView(position: Int) {
        val color = ContextCompat.getColor(this, R.color.pink)
        when (position) {
            1 -> {
                clearView()
                tx1.setTextColor(color)
                view1.visibility = View.VISIBLE

            }
            2 -> {
                clearView()
                tx2.setTextColor(color)
                view2.visibility = View.VISIBLE
            }
            3 -> {
                clearView()
                tx3.setTextColor(color)
                view3.visibility = View.VISIBLE
            }
            4 -> {
                clearView()
                tx4.setTextColor(color)
                view4.visibility = View.VISIBLE
            }
            5 -> {
                clearView()
                tx5.setTextColor(color)
                view5.visibility = View.VISIBLE
            }
        }
    }

    class ViewPagerAdapter(views: List<View>) : PagerAdapter() {
        var views1 = arrayListOf<View>()
        init {
            views1 = views as ArrayList<View>
        }

        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            return p0 == p1
        }

        override fun getCount(): Int {
            return views1.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(views1.get(position))
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(views1[position])
            initView(position, container)
            return views1[position]
        }

        fun initView(position: Int, container: ViewGroup) {
            views1[position].findViewById<TextView>(R.id.text).text = container.context.getString(R.string.text) + position.toShort()
        }
    }
}