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

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.CartAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.ParseHelper;
import com.project.mobileonline.utils.SetColoStatusBar;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.ADDRESS;
import static com.project.mobileonline.models.Constants.CART_ODER_ID;
import static com.project.mobileonline.models.Constants.FIRSTNAME;
import static com.project.mobileonline.models.Constants.LASTNAME;
import static com.project.mobileonline.models.Constants.ORDER_CONTENT;
import static com.project.mobileonline.models.Constants.ORDER_QUANTITY;
import static com.project.mobileonline.models.Constants.ORDER_STATUS;
import static com.project.mobileonline.models.Constants.ORDER_TABLE;
import static com.project.mobileonline.models.Constants.ORDER_TYPE;
import static com.project.mobileonline.models.Constants.PHONE;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.ROLE_GUEST;
import static com.project.mobileonline.models.Constants.SHIP_QUANTITY;
import static com.project.mobileonline.models.Constants.STATUS_PROGRESS;
import static com.project.mobileonline.models.Constants.TOTAL_AMOUNT;
import static com.project.mobileonline.models.Constants.TYPE_SHIP;
import static com.project.mobileonline.models.Constants.TYPE_STORE;
import static com.project.mobileonline.models.Constants.USER_ID;

public class CartActivity extends AppCompatActivity {
    Button order;
    ListView listProductInCart;
    CartAdapter adapter;
    int totalMoney = 0;
    int quantity = 0;
    ArrayList<ParseObject> carts = new ArrayList<>();

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
        ParseQuery<ParseObject> query = ParseHelper.getOrderedProductLocal();
        try {
            carts = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new CartAdapter(getBaseContext(), R.layout.item_cart_layout, carts);
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
                final EditText nameOrder = (EditText) view.findViewById(R.id.nameOrder);
                final EditText phoneOrder = (EditText) view.findViewById(R.id.phoneOrder);
                final EditText emailOrder = (EditText) view.findViewById(R.id.emailOrder);
                final EditText addressOder = (EditText) view.findViewById(R.id.addressOrder);
                final RadioButton ship = (RadioButton) view.findViewById(R.id.ship);
                final ParseUser user = ParseUser.getCurrentUser();
                if (user != null) {
                    String firstname = user.getString(FIRSTNAME) == null ? "" : user.getString(FIRSTNAME);
                    String lastname = user.getString(LASTNAME) == null ? "" : user.getString(LASTNAME);
                    nameOrder.setText(lastname + " " + firstname);
                    phoneOrder.setText(user.getString(PHONE));
                    emailOrder.setText(user.getEmail());
                    addressOder.setText(user.getString(ADDRESS));
                }
                builder.setView(view);
                builder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder content = new StringBuilder();
                        content.append("Name: ");
                        content.append(nameOrder.getText());
                        content.append("\n");
                        content.append("Phone: ");
                        content.append(phoneOrder.getText());
                        content.append("\n");
                        content.append("email: ");
                        content.append(emailOrder.getText());
                        content.append("\n");
                        content.append("Address: ");
                        content.append(addressOder.getText());
                        content.append("\n");

                        final ParseObject order = new ParseObject(ORDER_TABLE);
                        if (user != null) {
                            order.put(USER_ID, user);
                            order.setACL(new ParseACL(user));

                        }
                        for (int i = 0; i < carts.size(); i++) {
                            ParseObject product = carts.get(i).getParseObject(Constants.CART_PRODUCT_ID);
                            totalMoney += product.getInt(PRODUCT_PRICE) * carts.get(i).getInt(SHIP_QUANTITY);
                            quantity += carts.get(i).getInt(SHIP_QUANTITY);
                        }
                        order.put(TOTAL_AMOUNT, totalMoney);
                        order.put(ORDER_QUANTITY, quantity);
                        order.put(ORDER_STATUS, STATUS_PROGRESS);
                        if (ship.isChecked()) {
                            order.put(ORDER_TYPE, TYPE_SHIP);
                            content.append("Ship to house");
                        } else {
                            order.put(ORDER_TYPE, TYPE_STORE);
                            content.append("Receive at store");
                        }
                        order.put(ORDER_CONTENT, content.toString());



                        order.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    for (int i = 0; i < carts.size(); i++) {
                                        ParseObject cart = carts.get(i);
                                        cart.put(CART_ODER_ID, order);
                                        cart.saveInBackground();
                                        cart.unpinInBackground();
                                    }
                                    adapter.clear();
                                    adapter.notifyDataSetChanged();
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
                if (carts.size() == 0) {
                    Toast.makeText(getBaseContext(), "Nothing in Cart", Toast.LENGTH_SHORT).show();
                } else {
                    builder.show();
                }
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
