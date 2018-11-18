package com.example.homepage.recyclerview.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * created by tea9 at 2018/11/16
 */
public class MultipleItem implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
