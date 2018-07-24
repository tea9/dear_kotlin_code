package com.example.homepage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.homepage.R
import org.jetbrains.anko.find


/**
 * created by tea9 at 2018/7/21
 */
class ListViewAdapter(context: Context) : BaseExpandableListAdapter() {

    var groupString = arrayOf("--西游记", "--水浒传", "--三国演义", "--红楼梦")
    var childString = arrayOf(arrayOf("唐三藏", "孙悟空", "猪八戒", "沙和尚"), arrayOf("宋江", "林冲", "李逵", "鲁智深"), arrayOf("曹操", "刘备", "孙权", "诸葛亮", "周瑜"), arrayOf("贾宝玉", "林黛玉", "薛宝钗", "王熙凤"))


    var context: Context
//    var data:List<T>? = null
    var inflater:LayoutInflater
//
    init {
        this.context = context
//        this.data = data
        this.inflater = LayoutInflater.from(context)
    }

    override fun getGroup(p0: Int): Any? {
        return groupString[p0]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var viewHolder:GroupViewHolder? = null
        var view:View
        if (p2 == null) {
            view = inflater.inflate(R.layout.item_layout,p3,false)
            viewHolder = GroupViewHolder(view)
            view.tag = viewHolder
        } else {
            view = p2
            viewHolder = view.tag as GroupViewHolder
        }
        val item = getGroup(p0)
        if (item is String) {
            viewHolder.textview.text = item
        }
        return view!!
    }

    override fun getChildrenCount(p0: Int): Int {
        return childString?.size?:0
    }

    override fun getChild(p0: Int, p1: Int): Any? {
        return childString[p0][p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {

        var viewHolder : ChildViewHolder? = null
        var view:View
        if (null == p3) {
//            view = View.inflate(context,R.layout.item_textview,null)
            view = inflater.inflate(R.layout.item_layout,p4,false)
            viewHolder = ChildViewHolder(view)
            view.tag = viewHolder
        } else {
            view = p3
            viewHolder = view.tag as ChildViewHolder
        }

        val item = getChild(p0,p1)
        if (item is String) {
            viewHolder.textview.text = item
        }
        return view!!
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun getGroupCount(): Int {
        return groupString.size
    }

    class GroupViewHolder(itemView:View) {

        var textview: TextView = itemView.findViewById(R.id.item_tv)
    }

    class ChildViewHolder(itemView:View) {
        var textview: TextView = itemView.find(R.id.item_tv)
    }
}