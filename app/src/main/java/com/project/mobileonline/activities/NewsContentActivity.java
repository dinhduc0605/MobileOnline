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
        webView.loadData("<img src=\"http://www.joomlaworks.net/images/demos/galleries/abstract/7.jpg\" alt=\"Mountain View\">", "text/html", null);
    }
}
