package com.example.homepage.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homepage.R

/**
 * created by tea9 at 2018/9/18
 */
class QuickAdapter(dataSize:Int) : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_textview) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        when (helper!!.layoutPosition % 3){

        }
    }

}