package com.android.xio.zhihudailynews.task;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;
import com.android.xio.zhihudailynews.util.HttpUtil;
import com.android.xio.zhihudailynews.util.JsonUtil;

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

    public NewsContentTask() {
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
        protected void onPostExecute (NewsDetail newsDetail){
            String headerImage;
            if (newsDetail.getImage() == null || newsDetail.getImage() == "") {
                headerImage = "file:///android_asset/news_detail_header_image.jpg";

            } else {
                headerImage = newsDetail.getImage();
            }

            StringBuilder sb = new StringBuilder();
            sb.append("<div class=\"img-wrap\">")
                    .append("<h1 class=\"headline-title\">")
                    .append(newsDetail.getTitle()).append("</h1>")
                    .append("<span class=\"img-source\">")
                    .append(newsDetail.getImage_source()).append("</span>")
                    .append("<img src=\"").append(headerImage)
                    .append("\" alt=\"\">")
                    .append("<div class=\"img-mask\"></div>");
            String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                    + newsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
            webView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
        }
    }