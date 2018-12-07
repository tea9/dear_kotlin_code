package com.example.homepage.recyclerview.item;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.homepage.recyclerview.adapter.ExpandableItemAdapter;


/**
 * created by tea9 at 2018/11/22
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {

    public String title;
    public String subTitle;

    public Level0Item(String title,String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }
}
