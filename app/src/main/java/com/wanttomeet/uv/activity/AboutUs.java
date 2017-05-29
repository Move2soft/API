package com.wanttomeet.uv.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.wanttomeet.uv.R;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.URLs;

import butterknife.ButterKnife;

public class AboutUs extends BaseActivity {

    WebView webView_about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        bindToolbar();
        showEmptyOnly();
        setTitle("About US");
        showBack();


        if (isOnline()) {
            bind();
        } else {
            Toast.makeText(AboutUs.this, "Sorry! Not connected to internet", Toast.LENGTH_SHORT).show();
        }


    }

    public void bind(){
        webView_about_us= (WebView) findViewById(R.id.webView_about_us);
        webView_about_us.getSettings().setLoadsImagesAutomatically(true);
        webView_about_us.getSettings().setJavaScriptEnabled(true);
        webView_about_us.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
       // webView_about_us.setBackgroundColor(Color.parseColor("#DCEDC8"));
        webView_about_us.loadUrl(URLs.Abouts_url);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
