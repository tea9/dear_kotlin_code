package com.example.homepage.recyclerview.item;

/**
 * created by tea9 at 2018/11/16
 */
public class Video {
    private String img;
    private String name;

    public Video(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
