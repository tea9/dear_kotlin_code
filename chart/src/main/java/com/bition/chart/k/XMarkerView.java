package com.bition.chart.k;

import android.content.Context;
import android.widget.TextView;

import com.bition.chart.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;


public class XMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView tvMarker;
    private String time;

    public XMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvMarker = findViewById(R.id.tv_marker);
    }

    public void setData(String time) {
        this.time = time;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        tvMarker.setText(time);
    }

}
