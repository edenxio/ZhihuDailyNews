package com.android.xio.zhihudailynews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.xio.zhihudailynews.R;
import com.android.xio.zhihudailynews.db.NewsContentDB;
import com.android.xio.zhihudailynews.db.NewsDB;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;
import com.android.xio.zhihudailynews.task.NewsContentTask;
import com.android.xio.zhihudailynews.util.CheckNet;
import com.android.xio.zhihudailynews.util.WebViewUtil;

/**
 * 新闻内容显示界面
 * Created by Xio on 2016/6/29.
 */
public class NewsContent extends AppCompatActivity {
    private News news;
    private WebView webView;
    private boolean isConnected = true;

    private boolean isFavorite = false;
   @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        news = (News) getIntent().getSerializableExtra("news");
       isFavorite = NewsDB.getInstance(this).isFavorite(news);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
       isConnected = CheckNet.checkNetworkConnection(this);
       if (NewsContentDB.getInstance(this).isExist(news)){
           NewsDetail newsDetail = NewsContentDB.getInstance(this).getNewsDetail(news);
           WebViewUtil.showWebView(webView,newsDetail);
       }else{
           if (isConnected){
               new NewsContentTask(webView).execute(news.getId());
           }else{
               CheckNet.noNetworkAlert(this);
           }
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_favourite,menu);
        if (isFavorite){
            menu.findItem(R.id.favorite_button).setIcon(R.drawable.fav_active);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (isFavorite){
            NewsDB.getInstance(this).deleteNews(news);
            item.setIcon(R.drawable.fav_normal);
            isFavorite = false;
        }else{
            NewsDB.getInstance(this).saveNews(news);
            item.setIcon(R.drawable.fav_active);
            isFavorite = true;
        }
       return true;
    }

    public static void actionStart(Context context,News news){
        Intent intent = new Intent(context,NewsContent.class);
        intent.putExtra("news",news);
        context.startActivity(intent);
    }
}
