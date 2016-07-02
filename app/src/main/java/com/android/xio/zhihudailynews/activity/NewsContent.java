package com.android.xio.zhihudailynews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.xio.zhihudailynews.R;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.task.NewsContentTask;

/**
 * Created by Xio on 2016/6/29.
 */
public class NewsContent extends AppCompatActivity {
    private News news;
    private WebView webView;
    private static String LATEST_NEWS = "http://news-at.zhihu.com/api/4/news/";
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        news = (News) getIntent().getSerializableExtra("news");
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        new NewsContentTask(webView).execute(news.getId());

    }
    public static void actionStart(Context context,News news){
        Intent intent = new Intent(context,NewsContent.class);
        intent.putExtra("news",news);
        context.startActivity(intent);
    }
}
