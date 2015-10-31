package com.project.mobileonline.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.SetColoStatusBar;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    Button order;
    ListView listProductInCart;
    ProductListViewAdapter adapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        getControlWidget();

    }

    private void initView() {
        SetColoStatusBar.setColor(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order = (Button) findViewById(R.id.orderBtn);
        listProductInCart = (ListView) findViewById(R.id.listPorductInCart);
        products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product());
        }
        adapter = new ProductListViewAdapter(this, R.layout.list_item_product_categories, products);
        listProductInCart.setAdapter(adapter);


    }

    private void getControlWidget() {
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this, R.style
                        .MyDialog);
                builder.setTitle("IPhone 6s");
                builder.setView(R.layout.oder_dialog);
                builder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
