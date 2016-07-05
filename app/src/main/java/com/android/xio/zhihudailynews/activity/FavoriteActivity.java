package com.android.xio.zhihudailynews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ListView;

import com.android.xio.zhihudailynews.R;
import com.android.xio.zhihudailynews.adapter.NewsAdapter;
import com.android.xio.zhihudailynews.db.NewsDB;

/**
 * 收藏夹界面
 * Created by Xio on 2016/7/2.
 */
public class FavoriteActivity extends AppCompatActivity {
    private ListView favListView;
    private NewsAdapter favNewsAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_layout);
        favNewsAdapter = new NewsAdapter(this,R.layout.list_view_item,NewsDB.getInstance(this).loadFavorite());
        favListView = (ListView)findViewById(R.id.list_view_favourite);
        favListView.setAdapter(favNewsAdapter);
        favListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsContent.actionStart(FavoriteActivity.this,favNewsAdapter.getItem(position));
            }
        });
    }

    /**
     * 返回收藏夹界面时，刷新
     */
    @Override
    protected void onResume(){
        super.onResume();
       // Log.d("fav","onResume");
        favNewsAdapter = new NewsAdapter(this,R.layout.list_view_item,NewsDB.getInstance(this).loadFavorite());
        favListView.setAdapter(favNewsAdapter);
    }
    public static void startFavoriteActivity(Context context){
        Intent intent = new Intent(context,FavoriteActivity.class);
        context.startActivity(intent);
    }
}
