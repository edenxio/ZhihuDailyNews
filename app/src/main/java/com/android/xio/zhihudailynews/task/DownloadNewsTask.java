package com.android.xio.zhihudailynews.task;

import android.os.AsyncTask;

import com.android.xio.zhihudailynews.ZhihuDailyNewsApplication;
import com.android.xio.zhihudailynews.activity.NewsContent;
import com.android.xio.zhihudailynews.db.NewsContentDB;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;
import com.android.xio.zhihudailynews.util.HttpUtil;
import com.android.xio.zhihudailynews.util.JsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Xio on 2016/7/4.
 */
public class DownloadNewsTask extends AsyncTask<List<News>,Void,Void>{

    private NewsDetail newsDetail;
    @Override
    protected Void doInBackground(List<News>...params){
            for (int i =0;i<params[0].size();i++){
                try{
                   newsDetail = JsonUtil.parseJsonToDetail(HttpUtil.getNewsDetail(params[0].get(i).getId())) ;
                    if (!NewsContentDB.getInstance(ZhihuDailyNewsApplication.getContext()).isExist(newsDetail)){
                        NewsContentDB.getInstance(ZhihuDailyNewsApplication.getContext()).saveNewsContent(newsDetail,params[0].get(i).getId());
                    }
                }catch (IOException |JSONException e){
                    e.printStackTrace();
                }

            }return null;
    }

}
