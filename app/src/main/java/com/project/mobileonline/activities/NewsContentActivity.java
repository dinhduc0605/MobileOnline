package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.project.mobileonline.R;

/**
 * Created by Nguyen Dinh Duc on 10/14/2015.
 */
public class NewsContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        WebView webView = (WebView) findViewById(R.id.newsContent);
        webView.loadUrl("http://fptshop.com.vn/tin-tuc/tin-moi/philips-lo-anh-smartphone-khung-co-cam-bien-van-tay-33262");
    }
}
