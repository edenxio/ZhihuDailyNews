package com.android.xio.zhihudailynews.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.xio.zhihudailynews.R;
import com.android.xio.zhihudailynews.adapter.NewsAdapter;
import com.android.xio.zhihudailynews.task.NewsAsyncTask;
import com.android.xio.zhihudailynews.util.CheckNet;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private NewsAdapter adapter;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        isConnected = CheckNet.checkNetworkConnection(this);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        listView = (ListView)findViewById(R.id.list_view);
        adapter = new NewsAdapter(this,R.layout.list_view_item);
        listView.setAdapter(adapter);
        if (isConnected) new NewsAsyncTask(adapter).execute();
        else CheckNet.noNetworkAlert(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsContent.actionStart(MainActivity.this,adapter.getItem(position));
            }
        });

    }
    public void onRefresh(){
        if (isConnected) {
            new NewsAsyncTask(adapter, new NewsAsyncTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute();
        } else {
            CheckNet.noNetworkAlert(MainActivity.this);
            refreshLayout.setRefreshing(true);
        }
    }

}
