package com.android.xio.zhihudailynews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xio on 2016/7/2.
 */
public class NewsOpenHelper extends SQLiteOpenHelper{

     //新闻收藏夹
    public static final String CREATE_NEWS = "create table News(" +"id integer,"+"title text,"+"image text)";
    //主界面新闻列表
    public static final String CREATE_NEWS_LIST = "create table NewsList("+"id integer,"+"title text,"+"image text)";
    //新闻内容
    public static final String CREATE_NEWS_CONTENT = "create table NewsContent("
            +"newsId integer,"
            +"body text,"
            + "image text,"+"image_source text,"
            +"title text)";
    public NewsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_NEWS);
        db.execSQL(CREATE_NEWS_LIST);
        db.execSQL(CREATE_NEWS_CONTENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS News" );
        db.execSQL("DROP TABLE IF EXISTS NewsList");
        db.execSQL("DROP TABLE IF EXISTS NewsContent");
        onCreate(db);
    }
}
