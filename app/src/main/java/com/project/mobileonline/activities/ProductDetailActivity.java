package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.PreviewProductAdapter;
import com.project.mobileonline.adapters.SlideAdapter;
import com.project.mobileonline.utils.SetColoStatusBar;

public class ProductDetailActivity extends AppCompatActivity {
    RecyclerView previewProductSlide;
    PreviewProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        getControlWidget();

    }

    private void initView() {
        SetColoStatusBar.setColor(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Iphone 6S");

        adapter = new PreviewProductAdapter(this);
        previewProductSlide = (RecyclerView) findViewById(R.id.previewProductSlide);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        previewProductSlide.setLayoutManager(layoutManager);
        previewProductSlide.setAdapter(adapter);
    }

    private void getControlWidget() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
