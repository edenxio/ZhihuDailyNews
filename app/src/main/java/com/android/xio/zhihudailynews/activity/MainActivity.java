package com.android.xio.zhihudailynews.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.xio.zhihudailynews.R;
import com.android.xio.zhihudailynews.adapter.NewsAdapter;
import com.android.xio.zhihudailynews.db.NewsContentDB;
import com.android.xio.zhihudailynews.db.NewsListDB;
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.task.DownloadNewsTask;
import com.android.xio.zhihudailynews.task.NewsAsyncTask;
import com.android.xio.zhihudailynews.util.CheckNet;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private NewsAdapter adapter;
    private boolean isConnected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        isConnected = CheckNet.checkNetworkConnection(this);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        listView = (ListView)findViewById(R.id.list_view);
        adapter = new NewsAdapter(this,R.layout.list_view_item);
        if (isConnected) {
            new NewsAsyncTask(adapter).execute();

        }
        else {
            adapter = new NewsAdapter(this,R.layout.list_view_item, NewsListDB.getInstance(this).loadNewsList());
            CheckNet.noNetworkAlert(this);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsContent.actionStart(MainActivity.this,adapter.getItem(position));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_favourite:
                FavoriteActivity.startFavoriteActivity(this);
                break;
            case R.id.action_clean:
                NewsListDB.getInstance(this).deleteNewsList();
                NewsContentDB.getInstance(this).deleteNewsContent();
                break;
            case R.id.action_download:
                List<News> newsList =NewsListDB.getInstance(this).loadNewsList();
                new DownloadNewsTask().execute(newsList);
                break;
            default:
        }
        return true;
    }

    public void onRefresh(){

        isConnected = CheckNet.checkNetworkConnection(this);
        if (isConnected) {
            //adapter = new NewsAdapter(this,R.layout.list_view_item);
            new NewsAsyncTask(adapter, new NewsAsyncTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute();
        } else {
            adapter = new NewsAdapter(this,R.layout.list_view_item, NewsListDB.getInstance(this).loadNewsList());
            CheckNet.noNetworkAlert(MainActivity.this);
            refreshLayout.setRefreshing(false);
        }
    }

}
