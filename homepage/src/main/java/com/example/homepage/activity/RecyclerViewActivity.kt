package com.example.homepage.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.homepage.R
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * created by tea9 at 2018/9/18
 */
class RecyclerViewActivity : AppCompatActivity() {

    var adapter=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = OrientationHelper.VERTICAL
        recycler_view.layoutManager = layoutManager
        //(layoutResId:Int,sectionHeadResId:Int,data:List<SectionEntity<String>>): BaseSectionQuickAdapter<SectionEntity<String>,BaseViewHolder>(layoutResId,sectionHeadResId,data) {

//        var
//        adapter = SectionAdapter(R.layout.item_textview,R.layout.item_textview,data)


    }

}