package com.android.xio.zhihudailynews.entity;

import java.io.Serializable;

/**
 * Created by Xio on 2016/6/27.
 */
public class News implements Serializable {
    private int id;
    private String title;
    private String image;

    public News(){}
    public News(int id,String title,String image){
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public  void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return image;
    }
}
