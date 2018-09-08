package com.example.homepage.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homepage.R
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.support.v4.toast
import java.nio.charset.Charset

/**
 * created by tea9 at 2018/7/10
 */
class TabFragment: Fragment(){
    var pageType:Int = 0
    var data:String = ""
    var adapter:BaseQuickAdapter<String,BaseViewHolder>? = null

    companion object TabFragment{
        fun create(pageType:Int,data:String) : com.example.homepage.fragment.TabFragment {
            var f = TabFragment()
            var args = Bundle()
            args.putInt("position",pageType)
            args.putString("data",data)
            f.arguments=args
            return f
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var args: Bundle = arguments!!
        if (null!=args) {
            pageType = args.getInt("position")
            data = args.getString("data")
            Log.e("shaomiaodata",data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.recycler_view,container,false)
        initRecyclerView(view)
        return view
    }

    fun initRecyclerView(view:View) {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        var recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

//        var item:Array<String> = Gson().fromJson(data,Array<String>::class.java)
//        var list = arrayListOf<String>(data!!,"shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb")

        var list1=  data!!.fromListJson<String>()
        adapter = object : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_layout,list1 as List<String>) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.item_tv,item)
                helper.addOnClickListener(R.id.item_tv)
            }
        }
        adapter!!.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            toast("itemclick"+position)
        }
        recycler_view.adapter = adapter
    }

    inline fun <reified T> String.fromListJson(charset: Charset = Charset.forName("UTF-8")): ArrayList<T>? {
        val gson = GsonBuilder().create()
        return gson.fromJson<ArrayList<T>>(this.toByteArray(charset).toString(charset),object : TypeToken<ArrayList<T>>(){}.type)
    }

    fun updateArguments(pageType:Int,data:String) {
        this.pageType = pageType
        this.data = data
        var args: Bundle = arguments!!
        if (null!=args) {
            args.putInt("pageType",pageType)
            args.putString("data",data)
        }
    }


}