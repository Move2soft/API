package com.wanttomeet.uv.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.adapter.AdapterBanner;
import com.wanttomeet.uv.adapter.AdapterNews;
import com.wanttomeet.uv.model.Newslist;
import com.wanttomeet.uv.utils.AsyncHttpRequest;
import com.wanttomeet.uv.utils.AsyncResponseHandler;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.Debug;
import com.wanttomeet.uv.utils.URLs;

import butterknife.ButterKnife;

public class News extends BaseActivity{

    RecyclerView rv_news_all;
    AdapterNews adapterNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        bindToolbar();
        showEmptyOnly();
        setTitle("News");
        showBack();

        if (isOnline()) {
            bind();
        } else {
            Toast.makeText(News.this, "Sorry! Not connected to internet", Toast.LENGTH_SHORT).show();
        }


    }

    public void bind(){
        rv_news_all= (RecyclerView) findViewById(R.id.rv_news_all);
        adapterNews = new AdapterNews(News.this);
        RecyclerView.LayoutManager homelayoutmanager = new GridLayoutManager(News.this, 1);
        rv_news_all.setLayoutManager(homelayoutmanager);
        rv_news_all.setItemAnimator(new DefaultItemAnimator());
        rv_news_all.setAdapter(adapterNews);
        Newslistapi();
    }
    private void Newslistapi() {
        try {
            showProgress("");
            RequestParams params = new RequestParams();
            Debug.e("getHomeDetail", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

            clientPhotos.post(new URLs().Newslist, params, new RetryHandler(News.this));

        } catch (Exception e) {
            Debug.e("getHomeDetail Exception", e.getMessage());
        }
    }

    private class RetryHandler extends AsyncResponseHandler {

        public RetryHandler(Activity context) {
            super(context);
        }

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onFinish() {
            super.onFinish();
            try {

            } catch (Exception e) {
                Debug.e("RetryHandler finish Exception", e.getMessage());
            }
        }

        @Override
        public void onSuccess(String response) {
            try {
                dismissProgress();
                Log.e("responce", response);

                if (response != null && response.length() > 0) {

                    Newslist pt = new Gson().fromJson(
                            response, new TypeToken<Newslist>() {
                            }.getType());
                    adapterNews.addAll(pt.result);


                }

            } catch (Exception e) {
                Debug.e("RetryHandler Sucess Exception", e.getMessage());
            }
        }

        @Override
        public void onFailure(Throwable e, String content) {

            Log.e("f", content);
        }

    }

}
