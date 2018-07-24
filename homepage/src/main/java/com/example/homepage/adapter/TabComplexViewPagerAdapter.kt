package com.example.homepage.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homepage.R

/**
 * created by tea9 at 2018/7/20
 */
class TabComplexViewPagerAdapter(views1:ArrayList<View>, content1:Activity, data11: ArrayList<String>, data22: ArrayList<String>) : PagerAdapter() {
//    constructor(views1: ArrayList<View>, content1: TabComplexActivity, data11: ArrayList<String>, data22: ArrayList<String>) : this()


    var views = arrayListOf<View>()
    var content:Activity? = null
    var data1:ArrayList<String>? = null
    var data2:ArrayList<String>? = null

    init {
        views = views1
        content = content1
        data1 = data11
        data2 = data22
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return views.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        when(position) {
            0 ->{

                var headView = LayoutInflater.from(content).inflate(R.layout.item_tab_complex1_head,null)

                var rv1 = views[position].findViewById<RecyclerView>(R.id.recycler_view)
                var llm = LinearLayoutManager(content)
                llm.orientation = LinearLayoutManager.VERTICAL
                rv1.layoutManager = llm


                var rvadapter =object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_layout,data1 as List<String>) {
                    override fun convert(helper: BaseViewHolder?, item: String?) {
                        helper!!.setText(R.id.item_tv,item)
//                        helper.addOnClickListener(R.id.item_tv)
                    }
                }

                rvadapter.addHeaderView(headView)
                rv1.adapter = rvadapter

            }
            1 -> {
                var headView = LayoutInflater.from(content).inflate(R.layout.item_tab_complex2_head,null)
                var lv1 = views[position].findViewById<ExpandableListView>(R.id.list_view)
                lv1.setGroupIndicator(null)
                lv1.setAdapter(ListViewAdapter(content as Context))
                for (i in 0 until 4) {
                    lv1.expandGroup(i) //全部展开
                }
//                lv1.setOnGroupClickListener(return true)
                lv1.setOnGroupClickListener { expandableListView, view, i, l -> true }
                lv1.addHeaderView(headView)
            }
        }
        return views[position]
    }
}