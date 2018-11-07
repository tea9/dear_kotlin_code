package com.example.homepage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.homepage.R;

import java.lang.reflect.Field;

/**
 * created by tea9 at 2018/11/7
 * 各种方法获得控件
 */

public class HH extends AppCompatActivity{

    private TextView item_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        getView1();
        getView2();
        getView3();
        getView4();

    }

    private void getView1(){
        item_tv = findViewById(R.id.item_tv);
        item_tv.setText("text1");
    }
    private void getView2(){
        int viewId = getResources().getIdentifier("item_tv", "id", getPackageName());
        TextView textView =findViewById(viewId);
        textView.setText("text2");
    }

    private void getView3(){
        try {
            Field field = R.id.class.getField("item_tv");
            TextView textView = findViewById(field.getInt(null));
            textView.setText("text3");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private void getView4() {
        try {
            Class stuClass = Class.forName("com.example.homepage.activity.HH");
            Field file = stuClass.getDeclaredField("item_tv"); // 通过反射获取item_tv对象
            file.setAccessible(true);
            TextView textView = (TextView) file.get(this); // 获取textView实例
            textView.setText("text4");
            file.set(this,textView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
