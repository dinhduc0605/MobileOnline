package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Product;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class HistoryDetailActivity extends AppCompatActivity {
    ProductListViewAdapter adapter;
    ArrayList<Product> products;
    ListView listProductHistory;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        initView();
        getControlWidget();
    }

    private void initView() {
        SystemBarTintManager manager = new SystemBarTintManager(this);
        manager.setStatusBarTintEnabled(true);
        manager.setTintResource(R.color.primary_color);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        products = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            products.add(new Product());
        }
        adapter = new ProductListViewAdapter(this, R.layout.list_item_product_categories, products);
        listProductHistory = (ListView) findViewById(R.id.listProductHistory);
        listProductHistory.setAdapter(adapter);
    }

    private void getControlWidget() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
