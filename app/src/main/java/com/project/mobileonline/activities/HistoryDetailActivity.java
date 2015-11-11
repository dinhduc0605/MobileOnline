package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ViewSwitcher;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.HistoryOrderAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.ParseHelper;
import com.project.mobileonline.utils.SetColoStatusBar;

import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {
    public static final String TAG = "HistoryDetailActivity";
    HistoryOrderAdapter adapter;
    GridView orderedProductList;
    private ActionBar actionBar;
    ViewSwitcher viewSwitcher;

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
        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        orderedProductList = (GridView) findViewById(R.id.orderedProductList);
        Log.w(TAG, getIntent().getStringExtra("order"));
        ParseQuery.getQuery(Constants.ORDER_TABLE).getInBackground(getIntent().getStringExtra("order"), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                ParseQuery<ParseObject> query = ParseHelper.getOrderedProductList(parseObject);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            adapter = new HistoryOrderAdapter(HistoryDetailActivity.this, R.layout.history_order_item, list);
                            orderedProductList.setAdapter(adapter);
                            viewSwitcher.showNext();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
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
