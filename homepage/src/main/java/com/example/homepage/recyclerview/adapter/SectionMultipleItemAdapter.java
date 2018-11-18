package com.example.homepage.recyclerview.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.homepage.R;
import com.example.homepage.recyclerview.item.SectionMultipleItem;

import java.util.List;

/**
 * created by tea9 at 2018/11/16
 * 父子布局
 */
public class SectionMultipleItemAdapter extends BaseSectionQuickAdapter<SectionMultipleItem, BaseViewHolder> {


    /**
     * init SectionMultipleItemAdapter
     * 1. add your header resource layout
     * 2. add some kind of items
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionMultipleItemAdapter(int sectionHeadResId,int xxId, List data) {
        super(sectionHeadResId,xxId, data);
    }


    @Override
    protected void convertHead(BaseViewHolder helper, SectionMultipleItem item) {

        helper.setText(R.id.tv,item.getVideo().getName());

    }

    @Override
    protected void convert(BaseViewHolder helper, SectionMultipleItem item) {
        helper.setText(R.id.tv,item.getVideo().getName());
    }
}
