package com.example.homepage.recyclerview.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.homepage.R;
import com.example.homepage.recyclerview.item.MultipleItem;

import java.util.List;

/**
 * created by tea9 at 2018/11/16
 * 多item布局
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    public MultipleItemQuickAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.text_view);
        addItemType(MultipleItem.IMG, R.layout.image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
//                helper.setImageUrl(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG:
//                helper.setImageUrl(R.id.iv, item.getContent());

                break;
        }
    }

}
