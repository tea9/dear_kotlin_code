package com.example.homepage.recyclerview.item;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * created by tea9 at 2018/11/16
 */
public class MySection extends SectionEntity<Video> {

    private boolean isMore;
    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(Video t) {
        super(t);
    }


}
