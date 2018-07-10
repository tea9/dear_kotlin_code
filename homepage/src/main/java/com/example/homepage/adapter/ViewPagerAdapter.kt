package com.example.homepage.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.example.homepage.fragment.TabFragment
import com.google.gson.Gson

/**
 * created by tea9 at 2018/7/10
 */
class ViewPagerAdapter(fm: FragmentManager?, var tabList:ArrayList<String>, var listStr:Any) : FragmentPagerAdapter(fm){

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var fragment :TabFragment = super.instantiateItem(container, position) as TabFragment
        fragment.updateArguments(position, listStr.toString())
        return fragment
    }

    override fun getCount(): Int {
        return  tabList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabList[position]
    }


    override fun getItem(position: Int): Fragment {
        return  TabFragment.create(position, Gson().toJson(listStr))
    }


    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }
}