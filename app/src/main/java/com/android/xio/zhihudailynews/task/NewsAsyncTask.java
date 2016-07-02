package com.android.xio.zhihudailynews.task;

import android.os.AsyncTask;

import com.android.xio.zhihudailynews.adapter.NewsAdapter;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.util.HttpUtil;
import com.android.xio.zhihudailynews.util.JsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Xio on 2016/6/27.
 */
public class NewsAsyncTask extends AsyncTask<Void,Void,List<News>> {
        private NewsAdapter adapter;
        private onFinishListener listener;
    public NewsAsyncTask(NewsAdapter adapter){
        super();
        this.adapter = adapter;
    }
    public NewsAsyncTask(NewsAdapter adapter,onFinishListener listener){
        super();
        this.adapter = adapter;
        this.listener = listener;
    }

    @Override
    protected List<News> doInBackground(Void... voids) {
        List<News> newsList = null;
        try{
            newsList = JsonUtil.parseJSONToList(HttpUtil.getNewsList());
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }finally {
            return newsList;
        }
    }

    @Override
    protected void onPostExecute(List<News> newsList) {
        adapter.refreshNewsList(newsList);
        if (listener != null){
            listener.afterTaskFinish();
        }
    }
    public interface onFinishListener{
        public void afterTaskFinish();

    }
}
