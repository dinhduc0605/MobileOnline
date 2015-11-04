package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;

import com.project.mobileonline.R;
import com.project.mobileonline.utils.SetColoStatusBar;

/**
 * Created by Nguyen Dinh Duc on 10/14/2015.
 */
public class NewsContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        SetColoStatusBar.setColor(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        WebView webView = (WebView) findViewById(R.id.newsContent);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(
                "<head>\n" +
                "    <title>Example</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "</head>" + getIntent().getStringExtra("content"), "text/html", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
