package com.android.xio.zhihudailynews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.xio.zhihudailynews.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xio on 2016/7/2.
 */
public class NewsDB {
    public static final String DB_NAME = "news";
    public static final int VERSION = 1;
    private static NewsDB newsDB;
    private SQLiteDatabase db;
    private NewsOpenHelper dbHelper;

    private NewsDB(Context context) {
        dbHelper = new NewsOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static NewsDB getInstance(Context context){
        if (newsDB == null){
            newsDB = new NewsDB(context);
        }
        return newsDB;
    }

    /**
     * 将News实例存储到数据库
     * @param news
     */
    public void saveNews(News news){
        if (news !=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",news.getId());
            contentValues.put("title",news.getTitle());
            contentValues.put("Image",news.getImage());
            db.insert("News",null,contentValues);
        }
    }

    public void saveNewsList(News news){
        if(news != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",news.getId());
            contentValues.put("title",news.getTitle());
            contentValues.put("Image",news.getImage());
            db.insert("NewsList",null,contentValues);
        }
    }

    public List<News> loadFavorite(){
        List<News> favoriteList = new ArrayList<News>();
        Cursor cursor = db.query(DB_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                News news = new News();
                news.setId(cursor.getInt(0));
                news.setTitle(cursor.getString(1));
                news.setImage(cursor.getString(2));
                favoriteList.add(news);
            }while(cursor.moveToNext());
        } cursor.close();
        return favoriteList;
    }
    public boolean isFavorite(News news){
        Cursor cursor = db.query(DB_NAME,null,"id = ?",new String[]{news.getId()+""},null,null,null);
        if(cursor.moveToNext()){
            cursor.close();
            return true;
        } else {
            return  false;
        }
    }
    public void deleteNews(News news){
        db.delete(DB_NAME,"id = ?",new String[]{news.getId()+""});
    }
}
