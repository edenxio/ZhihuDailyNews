package com.android.xio.zhihudailynews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.xio.zhihudailynews.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xio on 2016/7/4.
 */
public class NewsListDB {
    public static final String DB_NAME = "newsList";
    public static final int VERSION = 1;
    private static NewsListDB newsListDB;
    private SQLiteDatabase db;
    private NewsOpenHelper dbHelper;

    private NewsListDB(Context context) {
        dbHelper = new NewsOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static NewsListDB getInstance(Context context){
        if (newsListDB == null){
            newsListDB = new NewsListDB(context);
        }
        return newsListDB;
    }

    /**
     * 将News实例存储到数据库
     * @param
     */


    public void saveNewsList(News news){
        if(news != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",news.getId());
            contentValues.put("title",news.getTitle());
            contentValues.put("Image",news.getImage());
            db.insert("NewsList",null,contentValues);
        }
    }

    public List<News> loadNewsList(){
        List<News> NewsList = new ArrayList<News>();
        Cursor cursor = db.query(DB_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                News news = new News();
                news.setId(cursor.getInt(0));
                news.setTitle(cursor.getString(1));
                news.setImage(cursor.getString(2));
                NewsList.add(news);
            }while(cursor.moveToNext());
        } cursor.close();
        return NewsList;
    }
    public boolean isExist(News news){
        Cursor cursor = db.query(DB_NAME,null,"id = ?",new String[]{news.getId()+""},null,null,null);
        if(cursor.moveToNext()){
            cursor.close();
            return true;
        } else {
            return  false;
        }
    }
    public void deleteNewsList(){
        db.delete(DB_NAME,null,null);
    }
}
