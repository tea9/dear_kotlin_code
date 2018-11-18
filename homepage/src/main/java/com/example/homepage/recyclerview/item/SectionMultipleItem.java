package com.example.homepage.recyclerview.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * created by tea9 at 2018/11/16
 */
public class SectionMultipleItem extends SectionEntity<Video> implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    private int itemType;
    private boolean isMore;
    private Video video;

    private List<Video> videos;


    public SectionMultipleItem(boolean isHeader, String header, Video video) {
        super(isHeader, header);
//        this.isMore = isMore;
        this.video = video;
    }

    // 不是头
    public SectionMultipleItem(int itemType, Video video) {
        super(video);
        this.video = video;
        this.itemType = itemType;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
