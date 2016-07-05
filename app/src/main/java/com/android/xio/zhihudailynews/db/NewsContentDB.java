package com.android.xio.zhihudailynews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.xio.zhihudailynews.activity.NewsContent;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;

/**
 * Created by Xio on 2016/7/4.
 */
public class NewsContentDB {
    private static final String DB_NAME = "newsContent";
    private static final int VERSION = 1;
    private static NewsContentDB newsContentDB;
    NewsOpenHelper dbHelper;
    SQLiteDatabase db;

    private NewsContentDB(Context context){
        dbHelper = new NewsOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static NewsContentDB getInstance(Context context){
        if (newsContentDB == null){
            newsContentDB = new NewsContentDB(context);
        }
        return newsContentDB;
    }
    public void saveNewsContent(NewsDetail newsDetail,int newsId){
        if(newsDetail != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("newsId",newsId);
            //contentValues.put("id",newsDetail.getId());
            contentValues.put("body",newsDetail.getBody());
            contentValues.put("image",newsDetail.getImage());
            contentValues.put("image_source",newsDetail.getImage_source());
            contentValues.put("title",newsDetail.getTitle());
            db.insert(DB_NAME,null,contentValues);
        }
    }
    public boolean isExist(NewsDetail newsDetail){
        Cursor cursor = db.query(DB_NAME,null,"title = ?",new String[]{newsDetail.getTitle()},null,null,null);
        if (cursor.moveToNext()){
            cursor.close();
            return true;
        }else{
            return false;
        }
    }
    public boolean isExist(News news){
        Cursor cursor = db.query(DB_NAME,null,"newsId = ?",new String[]{news.getId()+""},null,null,null);
        if (cursor.moveToNext()){
            cursor.close();
            return true;
        }else{
            return false;
        }
    }
    public NewsDetail getNewsDetail(News news){
        NewsDetail newsDetail= new NewsDetail();
        Cursor cursor = db.query(DB_NAME,null,"newsId = ?",new String[]{news.getId()+""},null,null,null);
        if (cursor.moveToFirst()){

                newsDetail.setBody(cursor.getString(cursor.getColumnIndex("body")));
                newsDetail.setImage(cursor.getString(cursor.getColumnIndex("image")));
                newsDetail.setImage_source(cursor.getString(cursor.getColumnIndex("image_source")));
                newsDetail.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                cursor.close();
        }return newsDetail;
    }
    public void deleteNewsContent(){
        db.delete(DB_NAME,null,null);
    }
}
