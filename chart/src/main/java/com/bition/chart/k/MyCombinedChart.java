package com.bition.chart.k;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.bition.chart.R;
import com.bition.chart.k.bean.KLineDataPares;
import com.fengniao.news.util.DateUtils;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.math.BigDecimal;

/**
 * created by tea9 at 2018/9/4
 */
public class MyCombinedChart extends CombinedChart{
    private KLineDataPares dataHelper;

    private XMarkerView xMarkerView;

    private YMarkerView yMarkerView;

    public MyCombinedChart(Context context) {
        super(context);
    }

    public MyCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        setNoDataText(getContext().getString(R.string.no_data_available));
        mRenderer = new MyCombinedChartRenderer(this, mAnimator, mViewPortHandler);
    }


    public void setMarkers(XMarkerView xMarkerView, YMarkerView yMarkerView, KLineDataPares dataHelper) {
        this.dataHelper = dataHelper;
        this.xMarkerView = xMarkerView;
        this.yMarkerView = yMarkerView;
    }



    @Override
    protected void drawMarkers(Canvas canvas) {
        if (!isDrawMarkersEnabled() || !valuesToHighlight() || dataHelper == null) return;
        for (int i = 0; i < mIndicesToHighlight.length; i++) {
            Highlight highlight = mIndicesToHighlight[i];

            IDataSet set = mData.getDataSetByIndex(highlight.getDataSetIndex());

            Entry e = mData.getEntryForHighlight(highlight);

            int xIndex = (int) highlight.getX();

//            int entryIndex = set.getEntryIndex(e);

            // make sure entry not null
            if (e == null || xIndex > set.getEntryCount() * mAnimator.getPhaseX())
                continue;

            float[] pos = getMarkerPosition(highlight);

            // check bounds
            if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                continue;

            if (xMarkerView != null) {
//                xMarkerView.setData(DateUtils.INSTANCE.longToString("yyyy:MM:dd HH:mm",
//                        Long.parseLong(dataHelper.getkLineDatas().get(xIndex).date)));
                xMarkerView.setData(DateUtils.INSTANCE.longToString("MM-dd HH:mm",
                        Long.parseLong(dataHelper.getkLineDatas().get(xIndex).date)));
                xMarkerView.refreshContent(e, highlight);
                xMarkerView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                xMarkerView.layout(0, 0, xMarkerView.getMeasuredWidth(),
                        xMarkerView.getMeasuredHeight());
                xMarkerView.draw(canvas, pos[0] - xMarkerView.getWidth() / 2, mViewPortHandler.contentBottom());
            }

            if (yMarkerView != null) {
                String yValForHighlight = new BigDecimal(highlight.getY()).toPlainString();
//                String.format(yValForHighlight, )
//                NumberFormat ddf1=NumberFormat.getNumberInstance() ;

//                String[] strs=yValForHighlight.split(".");
//                if(strs[1].length()>=8){
//                    strs[1] = strs[1].substring(0,8);
//                }

//                ddf1.setMaximumFractionDigits(2);
//                String s= ddf1.format(yValForHighlight) ;
//                yValForHighlight.substring()

                yMarkerView.setData(yValForHighlight);
                yMarkerView.refreshContent(e, highlight);
                yMarkerView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                yMarkerView.layout(0, 0, yMarkerView.getMeasuredWidth(),
                        yMarkerView.getMeasuredHeight());

                yMarkerView.draw(canvas, mViewPortHandler.contentRight(), highlight.getYPx() - yMarkerView.getHeight() / 2);
            }
        }
    }
}
