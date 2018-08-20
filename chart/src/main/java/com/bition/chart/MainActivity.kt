package com.bition.chart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lineChart.setDrawBorders(true)
        var entries = arrayListOf<Entry>()
        for (i in 0..10)
            entries.add(Entry(i.toFloat(),(Math.random()*80).toFloat()))
        // LineDataSet is a line
        var lineDataSet = LineDataSet(entries,"wendu")
        lineDataSet.color = R.color.colorAccent
        lineDataSet.valueTextColor = R.color.colorPrimary
        var data = LineData(lineDataSet)
        lineChart.data = data
    }
}
