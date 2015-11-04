package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.HistoryOrderAdapter;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.HelperClass;
import com.project.mobileonline.utils.ParseHelper;
import com.project.mobileonline.utils.SetColoStatusBar;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderActivity extends AppCompatActivity {
    HistoryOrderAdapter adapter;
    GridView orderedProductList;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        initView();
        getControlWidget();
    }

    private void initView() {
        SetColoStatusBar.setColor(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        orderedProductList = (GridView) findViewById(R.id.orderedProductList);
        ParseHelper parseHelper = new ParseHelper();
        ParseQuery<ParseObject> query = parseHelper.getOrderedProductList(getIntent().getStringExtra("order"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                adapter = new HistoryOrderAdapter(getBaseContext(), R.layout.grid_item_layout, list);
            }
        });
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
