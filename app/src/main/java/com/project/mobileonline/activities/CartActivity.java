package com.project.mobileonline.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.SetColoStatusBar;

import java.util.ArrayList;

import static com.project.mobileonline.models.Constants.ADDRESS;
import static com.project.mobileonline.models.Constants.CART_ODER_ID;
import static com.project.mobileonline.models.Constants.FIRSTNAME;
import static com.project.mobileonline.models.Constants.LASTNAME;
import static com.project.mobileonline.models.Constants.ORDER_QUANTITY;
import static com.project.mobileonline.models.Constants.ORDER_STATUS;
import static com.project.mobileonline.models.Constants.ORDER_TABLE;
import static com.project.mobileonline.models.Constants.ORDER_TYPE;
import static com.project.mobileonline.models.Constants.PHONE;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_QUANTITY;
import static com.project.mobileonline.models.Constants.SHIP_QUANTITY;
import static com.project.mobileonline.models.Constants.STATUS_PROGRESS;
import static com.project.mobileonline.models.Constants.TOTAL_AMOUNT;
import static com.project.mobileonline.models.Constants.TYPE_SHIP;
import static com.project.mobileonline.models.Constants.TYPE_STORE;
import static com.project.mobileonline.models.Constants.USER_ID;

public class CartActivity extends AppCompatActivity {
    Button order;
    ListView listProductInCart;
    ProductListViewAdapter adapter;
    ArrayList<ParseObject> products = new ArrayList<>();
    int totalMoney = 0;
    int quantity = 0;

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
        for (int i = 0; i < MainActivity.carts.size(); i++) {
            ParseObject product = MainActivity.carts.get(i).getParseObject(Constants.CART_PRODUCT_ID);
            product.put(PRODUCT_QUANTITY, MainActivity.carts.get(i).getInt(SHIP_QUANTITY));
            products.add(product);
            totalMoney += product.getInt(PRODUCT_PRICE);
            quantity += product.getInt(PRODUCT_QUANTITY);
        }
        order = (Button) findViewById(R.id.orderBtn);
        listProductInCart = (ListView) findViewById(R.id.listPorductInCart);
        adapter = new ProductListViewAdapter(this, R.layout.list_item_product_categories, products);
        listProductInCart.setAdapter(adapter);


    }

    private void getControlWidget() {
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this, R.style
                        .MyDialog);
                builder.setTitle("Bill");
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.oder_dialog, null);
                EditText nameOrder = (EditText) view.findViewById(R.id.nameOrder);
                EditText phoneOrder = (EditText) view.findViewById(R.id.phoneOrder);
                EditText emailOrder = (EditText) view.findViewById(R.id.emailOrder);
                EditText addressOder = (EditText) view.findViewById(R.id.addressOrder);
                final RadioButton ship = (RadioButton) view.findViewById(R.id.ship);
                RadioButton store = (RadioButton) view.findViewById(R.id.store);
                final ParseUser user = ParseUser.getCurrentUser();
                if (user != null) {
                    nameOrder.setText(user.getString(LASTNAME) + user.getString(FIRSTNAME));
                    phoneOrder.setText(user.getString(PHONE));
                    emailOrder.setText(user.getEmail());
                    addressOder.setText(user.getString(ADDRESS));
                }
                builder.setView(view);
                builder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ParseObject order = new ParseObject(ORDER_TABLE);
                        if (user != null) {
                            order.put(USER_ID, user);
                        }
                        order.put(TOTAL_AMOUNT, totalMoney);
                        order.put(ORDER_QUANTITY, quantity);
                        order.put(ORDER_STATUS, STATUS_PROGRESS);
                        if (ship.isChecked()) {
                            order.put(ORDER_TYPE, TYPE_SHIP);
                        } else {
                            order.put(ORDER_TYPE, TYPE_STORE);
                        }
                        order.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    for (int i = 0; i < MainActivity.carts.size(); i++) {
                                        ParseObject cart = MainActivity.carts.get(i);
                                        cart.put(CART_ODER_ID, order);
                                        cart.saveInBackground();
                                    }
                                    Toast.makeText(getBaseContext(), "Order successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
