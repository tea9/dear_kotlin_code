package com.example.homepage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Leon on 2018/5/7.
 * 自定义listview，动态计算高度
 */
public class CustomExpandableListView1 extends ExpandableListView {
    public CustomExpandableListView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    public CustomExpandableListView1(Context context) {
        super(context);
    }
    public CustomExpandableListView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // TODO Auto-generated method stub
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//
//                MeasureSpec.AT_MOST);
//
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }

}

