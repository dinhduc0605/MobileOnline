package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductGridViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.project.mobileonline.models.Constants.PRODUCT_QUERY;
import static com.project.mobileonline.models.Constants.PRODUCT_TABLE;

public class SearchActivity extends AppCompatActivity {
    public static final String TAG = "SearchActivity";
    ActionBar actionBar;
    ProgressBar progressBar;
    GridView gridView;
    ProductGridViewAdapter adapter;
    TextView resultSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SystemBarTintManager manager = new SystemBarTintManager(this);
        manager.setStatusBarTintEnabled(true);
        manager.setTintResource(R.color.primary_color);

        progressBar = (ProgressBar) findViewById(R.id.searchProgress);
        gridView = (GridView) findViewById(R.id.productSearchResult);
        resultSearch = (TextView) findViewById(R.id.resultSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem actionSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) actionSearch.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.query_hint));
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                searchProduct(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void searchProduct(String query) {
        String[] subStrings = query.split(" ");

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(PRODUCT_TABLE);
        parseQuery.whereContainsAll(PRODUCT_QUERY, Arrays.asList(subStrings));

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    String s = String.format(Locale.US, "Result: %d matches", list.size());
                    resultSearch.setText(s);
                    adapter = new ProductGridViewAdapter(SearchActivity.this, R.layout.grid_item_layout, new ArrayList<>(list));
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up long_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
