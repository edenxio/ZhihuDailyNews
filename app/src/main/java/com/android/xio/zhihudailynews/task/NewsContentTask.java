package com.android.xio.zhihudailynews.task;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;
import com.android.xio.zhihudailynews.util.HttpUtil;
import com.android.xio.zhihudailynews.util.JsonUtil;
import com.android.xio.zhihudailynews.util.WebViewUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Xio on 2016/7/2.
 */
public class NewsContentTask extends AsyncTask<Integer,Void,NewsDetail> {
    private NewsDetail newsDetail;
    private WebView webView;

    public NewsContentTask(WebView webView) {
        this.webView = webView;
    }

    @Override
    protected NewsDetail doInBackground(Integer... params) {
        try {
            newsDetail = JsonUtil.parseJsonToDetail(HttpUtil.getNewsDetail(params[0]));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            return newsDetail;
        }
    }

    @Override
    protected void onPostExecute(NewsDetail newsDetail) {
        WebViewUtil.showWebView(webView, newsDetail);
    }
}