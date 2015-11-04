package com.project.mobileonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductGridViewAdpater;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.ParseHelper;
import com.project.mobileonline.utils.SetColoStatusBar;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.NORMAL_PRODUCT;

public class ShowSpecificProduct extends AppCompatActivity {

    private ActionBar actionBar;
    ProductGridViewAdpater adpater;
    ArrayList<ParseObject> items = new ArrayList<>();
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_specific_product);
        initView();
        getControlWidget();
    }

    private void initView() {
        SetColoStatusBar.setColor(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        actionBar.setTitle(intent.getStringExtra("label"));
        int typeProduct = intent.getIntExtra("typeProduct", 0);
        ParseHelper parseHelper = new ParseHelper();
        ParseQuery<ParseObject> query = parseHelper.getProductQuery(typeProduct, NORMAL_PRODUCT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    adpater = new ProductGridViewAdpater(ShowSpecificProduct.this, R.layout.grid_item_layout, list);
                    gridView = (GridView) findViewById(R.id.specificProduct);
                    gridView.setAdapter(adpater);
                }
            }
        });
    }

    private void getControlWidget() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
