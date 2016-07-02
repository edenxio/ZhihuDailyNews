package com.android.xio.zhihudailynews.util;

/**
 * Created by Xio on 2016/6/27.
 */
import com.android.xio.zhihudailynews.entity.News;
import com.android.xio.zhihudailynews.entity.NewsDetail;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static List<News> parseJSONToList(String jsonData) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("stories");
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject newsInJson = jsonArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("images")){
                image =(String) newsInJson.getJSONArray("images").get(0);
            }
            News news = new News(id,title,image);
            newsList.add(news);
        }
        return newsList;
    }
    public static NewsDetail parseJsonToDetail(String json) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(json, NewsDetail.class);
    }
}


