package com.example.homepage.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homepage.entity.MySection
import com.example.homepage.R

/**
 * created by tea9 at 2018/9/18
 * group layout
 */
class SectionAdapter constructor(layoutResId:Int,sectionHeadResId:Int,data:List<MySection>): BaseSectionQuickAdapter<MySection,BaseViewHolder>(layoutResId,sectionHeadResId,data) {
    override fun convertHead(helper: BaseViewHolder?, item: MySection?) {
        helper!!.setText(R.id.text_view, item!!.t)

    }

    override fun convert(helper: BaseViewHolder?, item: MySection?) {
        helper!!.setText(R.id.text_view,item!!.t)
    }
}