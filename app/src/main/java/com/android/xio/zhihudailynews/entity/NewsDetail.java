package com.android.xio.zhihudailynews.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Xio on 2016/7/1.
 */
public class NewsDetail implements Serializable{
    private String body;
    private String image;
    private String image_source;
    private String title;
    private String share_url;
    private ArrayList<String> images;
    private String ga_prefix;
    private int id;
    private int type;
    public NewsDetail(){}

    public NewsDetail(String body, String image, String image_source, String title, String share_url, ArrayList<String> images, String ga_prefix, int id, String css, int type) {
        this.body = body;
        this.image = image;
        this.image_source = image_source;
        this.title = title;
        this.share_url = share_url;
        this.images = images;
        this.ga_prefix = ga_prefix;
        this.id = id;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }




    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }






    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
