package com.example.homepage.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.homepage.R;
import com.example.homepage.recyclerview.adapter.SectionMultipleItemAdapter;
import com.example.homepage.recyclerview.item.MultipleItem;
import com.example.homepage.recyclerview.item.SectionMultipleItem;
import com.example.homepage.recyclerview.item.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tea9 at 2018/11/16
 */
public class GroupLayoutActivity extends AppCompatActivity {

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recycler_view = findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//
        List<MultipleItem> data= new ArrayList<>();
        data.add(new MultipleItem(MultipleItem.TEXT));
        data.add(new MultipleItem(MultipleItem.IMG));


//
//        recycler_view.setAdapter(new MultipleItemQuickAdapter(data));
        List<SectionMultipleItem> mData = new ArrayList<>();
        mData.add(new SectionMultipleItem(true,"shaomiao",new Video("","bbbb")));

        mData.add(new SectionMultipleItem(false,"dfdf",new Video("","zzz")));
        mData.add(new SectionMultipleItem(false,"dfdf",new Video("","zzz")));
        mData.add(new SectionMultipleItem(false,"dfdf",new Video("","zzz")));

        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(true,"shaomiao",new Video("","bbbbb")));
        mData.add(new SectionMultipleItem(true,"shaomiao",new Video("","vvvvvv")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(true,"shaomiao",new Video("","vvvv")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        mData.add(new SectionMultipleItem(1,new Video("","aaaa")));
        recycler_view.setAdapter(new SectionMultipleItemAdapter(R.layout.text_view,R.layout.image_view,mData));
    }
}
