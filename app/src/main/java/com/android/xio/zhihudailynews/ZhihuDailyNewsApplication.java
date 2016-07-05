package com.android.xio.zhihudailynews;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Xio on 2016/7/2.
 */
public class ZhihuDailyNewsApplication extends Application {
    private static Context context;
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initImageLoader(this);

    }
    public static Context getContext(){
        return context;
    }

}
