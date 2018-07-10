package com.example.homepage.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homepage.R
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.support.v4.toast

/**
 * created by tea9 at 2018/7/10
 */
class HomeFragment: Fragment() {

    companion object {
        private val DATA_FLAG = "data_flag"

        fun create(str:String):HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(DATA_FLAG,str)
            fragment.arguments = args
            return fragment
        }

    }

    private var data:String? = null
    var adapter:BaseQuickAdapter<String,BaseViewHolder>? = null

    fun updateData(str:String) {
        this.data = str
        adapter!!.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            data = arguments!!.getString(DATA_FLAG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.recycler_view,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    fun initRecyclerView() {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

//        var item:Array<String> = Gson().fromJson(data,Array<String>::class.java)
        var list = arrayListOf<String>(data!!,"shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb","shaomiao","aaa","bbb")
        adapter = object : BaseQuickAdapter<String,BaseViewHolder>(R.layout.layout_item,list as List<String>) {
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
}