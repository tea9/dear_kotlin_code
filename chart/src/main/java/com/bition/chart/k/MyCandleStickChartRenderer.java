package com.bition.chart.k;

import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.renderer.CandleStickChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 * Created by Eiffe on 2018/1/31.
 */

public class MyCandleStickChartRenderer extends CandleStickChartRenderer {
    public MyCandleStickChartRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    public void drawValues(Canvas c) {

        List<ICandleDataSet> dataSets = mChart.getCandleData().getDataSets();

        for (int i = 0; i < dataSets.size(); i++) {

            ICandleDataSet dataSet = dataSets.get(i);

            if (!dataSet.isDrawValuesEnabled() || dataSet.getEntryCount() == 0)
                continue;

            // apply the text-styling defined by the DataSet
            applyValueTextStyle(dataSet);

            Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

            int minx = Math.max(mXBounds.min, 0);
            int maxx = Math.min(mXBounds.max + 1, dataSet.getEntryCount());

            float[] positions = trans.generateTransformedValuesCandle(
                    dataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), mXBounds.min, mXBounds.max);

            float yOffset = Utils.convertDpToPixel(5f);

//                for (int j = 0; j < positions.length; j += 2) {
//
//                    float x = positions[j];
//                    float y = positions[j + 1];
//
//                    if (!mViewPortHandler.isInBoundsRight(x))
//                        break;
//
//                    if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
//                        continue;
//
//                    CandleEntry entry = dataSet.getEntryForIndex(j / 2 + minx);
//
//                    drawValue(c, dataSet.getValueFormatter(), entry.getHigh(), entry, i, x, y - yOffset, dataSet.getValueTextColor(j / 2));
//                }

//                float[] numbers = new float[positions.length];
                /*List<Float> numbers=new ArrayList<>();
                for (int j = 0; j < positions.length; j += 2) {
                    float x = positions[j];
                    float y = positions[j + 1];

                    if (!mViewPortHandler.isInBoundsRight(x))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
                        continue;

                    CandleEntry entry = dataSet.getEntryForIndex(j / 2 + minx);
                    numbers.add(entry.getVal());
                }*/


            //绘制最大和最小值
                /*if(numbers.size()>0){
                    //dataSet.get
                    float max=numbers.get(0); // 把数据中的第1个元素存max
                    float min=numbers.get(0); // 把数据中的第1个元素存min
                    for(int k=1;k<numbers.size();k++){ // 从第二个元素开始遍历数组
                        if(numbers.get(k)>max){  // 假如元素大于max 就把当前值赋值给max
                            max=numbers.get(k);
                        }
                        if(numbers.get(k)<min){  // 假如元素小于min 就把当前值赋值给min
                            min=numbers.get(k);
                        }
                    }

                    for (int j = 0; j < positions.length; j += 2) {

                        float x = positions[j];
                        float y = positions[j + 1];

                        if (!mViewPortHandler.isInBoundsRight(x))
                            break;

                        if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
                            continue;

                        CandleEntry entry = dataSet.getEntryForIndex(j / 2 + minx);

                        if(entry.getVal()==max) {
                            drawValue(c, dataSet.getValueFormatter(), entry.getVal(), entry, i, x,
                                    y - yOffset, dataSet.getValueTextColor(j / 2));
                        }
                        if(entry.getVal()==min){
                            drawValue(c, dataSet.getValueFormatter(), entry.getVal(), entry, i, x,
                                    y + yOffset, dataSet.getValueTextColor(j / 2));
                        }
                    }
                }*/


            //计算最大值和最小值
            float maxValue = 0, minValue = 0;
            int maxIndex = 0, minIndex = 0;
            CandleEntry maxEntry = null, minEntry = null;
            boolean firstInit = true;
            for (int j = 0; j < positions.length; j += 2) {

                float x = positions[j];
                float y = positions[j + 1];

                if (!mViewPortHandler.isInBoundsRight(x))
                    break;

                if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
                    continue;

                CandleEntry entry = dataSet.getEntryForIndex(j / 2 + minx);

                if (firstInit) {
                    maxValue = entry.getHigh();
                    minValue = entry.getLow();
                    firstInit = false;
                    maxEntry = entry;
                    minEntry = entry;
                } else {
                    if (entry.getHigh() > maxValue) {
                        maxValue = entry.getHigh();
                        maxIndex = j;
                        maxEntry = entry;
                    }

                    if (entry.getLow() < minValue) {
                        minValue = entry.getLow();
                        minIndex = j;
                        minEntry = entry;
                    }

                }
            }

            //绘制最大值和最小值
            float x = positions[minIndex];
            float y = positions[minIndex + 1];
            if (maxIndex > minIndex) {
                //画右边
                String highString = "← " + Float.toString(minValue);

                //计算显示位置
                //计算文本宽度
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);
                int highStringHeight = Utils.calcTextHeight(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[1] = minValue;
                trans.pointValuesToPixel(tPosition);
                mValuePaint.setColor(dataSet.getValueTextColor(minIndex / 2));
                c.drawText(highString, x + highStringWidth / 2, tPosition[1], mValuePaint);
            } else {
                //画左边
                String highString = Float.toString(minValue) + " →";

                //计算显示位置
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);
                int highStringHeight = Utils.calcTextHeight(mValuePaint, highString);

                    /*mValuePaint.setColor(dataSet.getValueTextColor(minIndex / 2));
                    c.drawText(highString, x-highStringWidth/2, y+yOffset, mValuePaint);*/
                float[] tPosition = new float[2];
                tPosition[1] = minValue;
                trans.pointValuesToPixel(tPosition);
                mValuePaint.setColor(dataSet.getValueTextColor(minIndex / 2));
                c.drawText(highString, x - highStringWidth / 2, tPosition[1], mValuePaint);
            }

            x = positions[maxIndex];
            y = positions[maxIndex + 1];
            if (maxIndex > minIndex) {
                //画左边
                String highString = Float.toString(maxValue) + " →";

                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);
                int highStringHeight = Utils.calcTextHeight(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[0] = maxEntry == null ? 0f : maxEntry.getX();
                tPosition[1] = maxEntry == null ? 0f : maxEntry.getHigh();
                trans.pointValuesToPixel(tPosition);

                mValuePaint.setColor(dataSet.getValueTextColor(maxIndex / 2));
                //c.drawText(highString, x+highStringWidth , y-yOffset, mValuePaint);
                c.drawText(highString, tPosition[0] - highStringWidth / 2, tPosition[1], mValuePaint);

                    /*mValuePaint.setColor(dataSet.getValueTextColor(maxIndex / 2));
                    c.drawText(highString, x - highStringWidth, y-yOffset, mValuePaint);*/
            } else {
                //画右边
                String highString = "← " + Float.toString(maxValue);

                //计算显示位置
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);
                int highStringHeight = Utils.calcTextHeight(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[0] = maxEntry == null ? 0f : maxEntry.getX();
                tPosition[1] = maxEntry == null ? 0f : maxEntry.getHigh();
                trans.pointValuesToPixel(tPosition);

                mValuePaint.setColor(dataSet.getValueTextColor(maxIndex / 2));
                //c.drawText(highString, x+highStringWidth , y-yOffset, mValuePaint);
                c.drawText(highString, tPosition[0] + highStringWidth / 2, tPosition[1], mValuePaint);

            }

        }


    }
}
