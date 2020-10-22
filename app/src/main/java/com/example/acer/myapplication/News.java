package com.example.acer.myapplication;

import android.graphics.Bitmap;

/**
 * Created by acer on 2018/6/7.
 */

public class News {

    private String newsTitle;   //新闻标题
    private String newsUrl;     //新闻链接地址
    private String desc;
    private Bitmap bitmap;

    public News(String newsTitle, String newsUrl,String desc,Bitmap bitmap) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.desc = desc;
        this.bitmap = bitmap;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public Bitmap  getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
