package com.bition.chart.k.bean;


import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class KLineDataPares {


    //k线图数据
    private ArrayList<KLineBean> kLineDatas = new ArrayList<>();
    //成交量数据
    private ArrayList<BarEntry> barEntries = new ArrayList<>();
    //K线数据
    private ArrayList<CandleEntry> candleEntries = new ArrayList<>();
    //分时图数据
    private ArrayList<Entry> minuteEntries = new ArrayList<>();
    private ArrayList<Entry> volEntry = new ArrayList<>();

    //均线
    private ArrayList<Entry> ma5DataL = new ArrayList<>();
    private ArrayList<Entry> ma10DataL = new ArrayList<>();
    private ArrayList<Entry> ma20DataL = new ArrayList<>();
    private ArrayList<Entry> ma30DataL = new ArrayList<>();

    private ArrayList<Entry> ma5DataV = new ArrayList<>();
    private ArrayList<Entry> ma10DataV = new ArrayList<>();
    private ArrayList<Entry> ma20DataV = new ArrayList<>();
    private ArrayList<Entry> ma30DataV = new ArrayList<>();


    public KLineDataPares() {
    }

    public void clearData() {
        kLineDatas.clear();
        barEntries.clear();
        candleEntries.clear();
        minuteEntries.clear();
        volEntry.clear();
        ma5DataL.clear();
        ma10DataL.clear();
        ma20DataL.clear();
        ma30DataL.clear();
        ma5DataV.clear();
        ma10DataV.clear();
        ma20DataV.clear();
        ma30DataV.clear();
    }

    //初步解析k线图数据
    public void parseKLineData(JSONObject obj) {
        ArrayList<KLineBean> kLineBeans = new ArrayList<>();
        JSONArray list = obj.optJSONArray("data");
        if (list != null&&list.length()>0) {
            int count = list.length();
            for (int i = 0; i < count; i++) {
                JSONObject dayData = list.optJSONObject(i);
                KLineBean kLineData = new KLineBean();
                kLineData.date = dayData.optString("id") + "000";
                kLineData.open = new BigDecimal(dayData.optString("open")).floatValue();
                kLineData.close = new BigDecimal(dayData.optString("close")).floatValue();
                kLineData.high = new BigDecimal(dayData.optString("high")).floatValue();
                kLineData.low = new BigDecimal(dayData.optString("low")).floatValue();
                kLineData.vol = new BigDecimal(dayData.optString("vol")).floatValue();
                kLineBeans.add(kLineData);
//                closeMax = Math.max(kLineData.close, closeMax);
//                closeMin = Math.max(kLineData.close, closeMin);
//                volmax = Math.max(kLineData.vol, volmax);
//                xValuesLabel.put(i, kLineData.date);
            }
        }
        kLineDatas.addAll(kLineBeans);
    }

    public ArrayList<Entry> getVolEntry() {
        return volEntry;
    }

    public void setVolEntry(ArrayList<Entry> volEntry) {
        this.volEntry = volEntry;
    }

    public ArrayList<Entry> getMinuteEntries() {
        return minuteEntries;
    }

    public void setMinuteEntries(ArrayList<Entry> minuteEntries) {
        this.minuteEntries = minuteEntries;
    }

    //处理数据
    public void handleKLineData(ArrayList<KLineBean> list) {
        if (list == null||list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            KLineBean kLineBean = list.get(i);
            barEntries.add(new BarEntry(i, kLineBean.vol, kLineBean));
            candleEntries.add(new CandleEntry(i, kLineBean.high, kLineBean.low,
                    kLineBean.open, kLineBean.close, kLineBean));
            minuteEntries.add(new Entry(i, kLineBean.close, kLineBean));
            volEntry.add(new Entry(i, kLineBean.vol, kLineBean));

        }
    }


    /**
     * 初始化K线图均线
     *
     * @param datas
     */
    public void initKLineMA(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        ma5DataL.clear();
        ma10DataL.clear();
        ma20DataL.clear();
        ma30DataL.clear();

        KMAEntity kmaEntity5 = new KMAEntity(datas, 5);
        KMAEntity kmaEntity10 = new KMAEntity(datas, 10);
        KMAEntity kmaEntity20 = new KMAEntity(datas, 20);
        KMAEntity kmaEntity30 = new KMAEntity(datas, 30);
        for (int i = 0; i < kmaEntity5.getMAs().size(); i++) {
            ma5DataL.add(new Entry(i, kmaEntity5.getMAs().get(i)));
            ma10DataL.add(new Entry(i, kmaEntity10.getMAs().get(i)));
            ma20DataL.add(new Entry(i, kmaEntity20.getMAs().get(i)));
            ma30DataL.add(new Entry(i, kmaEntity30.getMAs().get(i)));
        }
    }

    /**
     * 初始化成交量均线
     *
     * @param datas
     */
    public void initVolumeMA(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        ma5DataV = new ArrayList<>();
        ma10DataV = new ArrayList<>();
        ma20DataV = new ArrayList<>();
        ma30DataV = new ArrayList<>();

        VMAEntity vmaEntity5 = new VMAEntity(datas, 5);
        VMAEntity vmaEntity10 = new VMAEntity(datas, 10);
        VMAEntity vmaEntity20 = new VMAEntity(datas, 20);
        VMAEntity vmaEntity30 = new VMAEntity(datas, 30);
        for (int i = 0; i < vmaEntity5.getMAs().size(); i++) {
            ma5DataV.add(new Entry(i, (vmaEntity5.getMAs().get(i))));
            ma10DataV.add(new Entry(i, vmaEntity10.getMAs().get(i)));
            ma20DataV.add(new Entry(i, vmaEntity20.getMAs().get(i)));
            ma30DataV.add(new Entry(i, vmaEntity30.getMAs().get(i)));
        }
    }

    /**
     * 计算MA(X,N):X的N日简单移动平均，算法(X1+X2+X3+...+Xn)/N
     *
     * @param data 二维数组 第一维 0表示原始数据列表 1表示计算的结果列表
     *             第二维 数据列表
     * @param n
     */
    private void calculateMA(double[][] data, int n) {
        if (data == null || data.length < 2 || data[0] == null || data[0].length == 0) {
            return;
        }
        for (int i = 0; i < data[0].length; i++) {
            if (i < n - 1) {
                data[1][i] = 0;
            } else {
                double sum = 0;
                for (int j = i; j > i - n && j >= 0; j--) {
                    sum += data[0][j];
                }
                data[1][i] = sum / n;
            }
        }
    }


    public ArrayList<KLineBean> getkLineDatas() {
        return kLineDatas;
    }

    public void setkLineDatas(ArrayList<KLineBean> kLineDatas) {
        this.kLineDatas = kLineDatas;
    }

    public ArrayList<BarEntry> getBarEntries() {
        return barEntries;
    }

    public void setBarEntries(ArrayList<BarEntry> barEntries) {
        this.barEntries = barEntries;
    }

    public ArrayList<CandleEntry> getCandleEntries() {
        return candleEntries;
    }

    public void setCandleEntries(ArrayList<CandleEntry> candleEntries) {
        this.candleEntries = candleEntries;
    }

    public ArrayList<Entry> getMa5DataL() {
        return ma5DataL;
    }

    public void setMa5DataL(ArrayList<Entry> ma5DataL) {
        this.ma5DataL = ma5DataL;
    }

    public ArrayList<Entry> getMa10DataL() {
        return ma10DataL;
    }

    public void setMa10DataL(ArrayList<Entry> ma10DataL) {
        this.ma10DataL = ma10DataL;
    }

    public ArrayList<Entry> getMa20DataL() {
        return ma20DataL;
    }

    public void setMa20DataL(ArrayList<Entry> ma20DataL) {
        this.ma20DataL = ma20DataL;
    }

    public ArrayList<Entry> getMa30DataL() {
        return ma30DataL;
    }

    public void setMa30DataL(ArrayList<Entry> ma30DataL) {
        this.ma30DataL = ma30DataL;
    }

    public ArrayList<Entry> getMa5DataV() {
        return ma5DataV;
    }

    public void setMa5DataV(ArrayList<Entry> ma5DataV) {
        this.ma5DataV = ma5DataV;
    }

    public ArrayList<Entry> getMa10DataV() {
        return ma10DataV;
    }

    public void setMa10DataV(ArrayList<Entry> ma10DataV) {
        this.ma10DataV = ma10DataV;
    }

    public ArrayList<Entry> getMa20DataV() {
        return ma20DataV;
    }

    public void setMa20DataV(ArrayList<Entry> ma20DataV) {
        this.ma20DataV = ma20DataV;
    }

    public ArrayList<Entry> getMa30DataV() {
        return ma30DataV;
    }

    public void setMa30DataV(ArrayList<Entry> ma30DataV) {
        this.ma30DataV = ma30DataV;
    }


}
