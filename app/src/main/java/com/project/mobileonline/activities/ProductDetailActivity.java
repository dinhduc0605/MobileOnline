package com.project.mobileonline.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.PreviewSmallSizeAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.SetColoStatusBar;
import com.project.mobileonline.utils.StringProcess;

import java.util.List;

import static com.project.mobileonline.models.Constants.BACKGROUND_IMAGE;
import static com.project.mobileonline.models.Constants.BACK_CAMERA;
import static com.project.mobileonline.models.Constants.BATTERY;
import static com.project.mobileonline.models.Constants.CART_PRODUCT_ID;
import static com.project.mobileonline.models.Constants.CHIPSET;
import static com.project.mobileonline.models.Constants.CONECTION;
import static com.project.mobileonline.models.Constants.CPU;
import static com.project.mobileonline.models.Constants.FRONT_CAMERA;
import static com.project.mobileonline.models.Constants.INTERNAL_STORAGE;
import static com.project.mobileonline.models.Constants.NUMBER_STAR;
import static com.project.mobileonline.models.Constants.OS;
import static com.project.mobileonline.models.Constants.PRODUCT_MANUFACTURE;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_QUANTITY;
import static com.project.mobileonline.models.Constants.RAM;
import static com.project.mobileonline.models.Constants.RATE_NUMBER_STAR;
import static com.project.mobileonline.models.Constants.RATE_PRODUCT;
import static com.project.mobileonline.models.Constants.RATE_TABLE;
import static com.project.mobileonline.models.Constants.RATE_USER;
import static com.project.mobileonline.models.Constants.SCREEN;
import static com.project.mobileonline.models.Constants.SDCARD;
import static com.project.mobileonline.models.Constants.SHIP_QUANTITY;
import static com.project.mobileonline.models.Constants.SHOPPING_CART_TABLE;
import static com.project.mobileonline.models.Constants.SMALL_SLIDE_IMAGE;
import static com.project.mobileonline.models.Constants.SPECIFICATION;

public class ProductDetailActivity extends AppCompatActivity {
    RecyclerView previewSmallSlide;
    PreviewSmallSizeAdapter smallSizeAdapter;
    ImageView backgroundProduct;
    TextView detailPrice, detailManufacture, detailProductQuantity, screenSpec, frontCameraSpec, backCameraSpec, osSpec,
            graphicSpec, cpuSpec, ramSpec, internalSpec, sdcardSpec, simSpec, batterySpec, connectSpec;
    RatingBar ratingBar;
    Button addToCart;
    ViewSwitcher viewSwitcher;
    ParseObject rate;
    ParseUser currentUser;

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
        getSupportActionBar().setTitle(getIntent().getStringExtra("productName"));

        currentUser = ParseUser.getCurrentUser();

        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        backgroundProduct = (ImageView) findViewById(R.id.backgroundProduct);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
        detailManufacture = (TextView) findViewById(R.id.detailManufacture);
        detailProductQuantity = (TextView) findViewById(R.id.detailProductQuantity);
        screenSpec = (TextView) findViewById(R.id.screenSpec);
        frontCameraSpec = (TextView) findViewById(R.id.frontCameraSpec);
        backCameraSpec = (TextView) findViewById(R.id.backCameraSpec);
        osSpec = (TextView) findViewById(R.id.osSpec);
        graphicSpec = (TextView) findViewById(R.id.graphicSpec);
        cpuSpec = (TextView) findViewById(R.id.cpuSpec);
        ramSpec = (TextView) findViewById(R.id.ramSpec);
        internalSpec = (TextView) findViewById(R.id.internalSpec);
        sdcardSpec = (TextView) findViewById(R.id.sdcardSpec);
        simSpec = (TextView) findViewById(R.id.simSpec);
        batterySpec = (TextView) findViewById(R.id.batterySpec);
        connectSpec = (TextView) findViewById(R.id.connectSpec);
        addToCart = (Button) findViewById(R.id.addToCart);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if (currentUser == null) {
            ratingBar.setVisibility(View.GONE);
        }

        previewSmallSlide = (RecyclerView) findViewById(R.id.previewProductSmallSlide);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        previewSmallSlide.setLayoutManager(layoutManager);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.PRODUCT_TABLE);

        query.getInBackground(getIntent().getStringExtra("productId"), new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject parseObject, ParseException e) {
                if (e == null) {
                    ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(RATE_TABLE);
                    parseQuery.whereEqualTo(RATE_USER, currentUser);
                    parseQuery.whereEqualTo(RATE_PRODUCT, parseObject);
                    parseQuery.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null && currentUser != null) {
                                if (list.size() > 0) {
                                    rate = list.get(0);
                                } else {
                                    rate = new ParseObject(RATE_TABLE);
                                    rate.put(RATE_USER, ParseUser.getCurrentUser());
                                    rate.put(RATE_PRODUCT, parseObject);
                                    rate.put(RATE_NUMBER_STAR, "0");
                                }
                                ratingBar.setRating(Float.parseFloat(rate.getString(RATE_NUMBER_STAR)));
                                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                    @Override
                                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                        //save numstar vao bang Rate
                                        rate.put(RATE_NUMBER_STAR, String.valueOf(rating));
                                        rate.saveInBackground();
                                        //tim cac rate cua product hien tai
                                        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(RATE_TABLE);
                                        parseQuery.whereEqualTo(RATE_PRODUCT, parseObject);
                                        parseQuery.findInBackground(new FindCallback<ParseObject>() {
                                            @Override
                                            public void done(List<ParseObject> list, ParseException e) {
                                                float total = 0;
                                                //tinh tong so star da dc rate cua product nay boi tat ca cac user
                                                for (int i = 0; i < list.size(); i++) {
                                                    total += Float.parseFloat(list.get(i).getString(RATE_NUMBER_STAR));
                                                }
                                                //luu so star trung binh vao bang Product
                                                parseObject.put(NUMBER_STAR, String.valueOf(total / list.size()));
                                                parseObject.saveInBackground();
                                            }
                                        });
                                        Toast.makeText(ProductDetailActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                    //Lay thong so ky thuat cua product hien tai
                    ParseObject specification = parseObject.getParseObject(SPECIFICATION);
                    specification.fetchIfNeededInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            screenSpec.setText(parseObject.getString(SCREEN));
                            frontCameraSpec.setText(parseObject.getString(FRONT_CAMERA));
                            backCameraSpec.setText(parseObject.getString(BACK_CAMERA));
                            osSpec.setText(parseObject.getString(OS));
                            graphicSpec.setText(parseObject.getString(CHIPSET));
                            cpuSpec.setText(parseObject.getString(CPU));
                            ramSpec.setText(parseObject.getString(RAM));
                            internalSpec.setText(parseObject.getString(INTERNAL_STORAGE));
                            sdcardSpec.setText(parseObject.getString(SDCARD));
                            simSpec.setText(parseObject.getString(SDCARD));
                            batterySpec.setText(parseObject.getString(BATTERY));
                            connectSpec.setText(parseObject.getString(CONECTION));
                        }
                    });
                    detailPrice.setText("Price: " + StringProcess.formatPrice((Integer) parseObject.getNumber(PRODUCT_PRICE)));
                    detailManufacture.setText("Manufacture: " + parseObject.getString(PRODUCT_MANUFACTURE));
                    detailProductQuantity.setText(getString(R.string.quantity) + parseObject.getNumber(PRODUCT_QUANTITY));
                    ImageLoader.getInstance().displayImage(parseObject.getString(BACKGROUND_IMAGE), backgroundProduct);
                    List<String> imageUrls = parseObject.getList(SMALL_SLIDE_IMAGE);
                    smallSizeAdapter = new PreviewSmallSizeAdapter(ProductDetailActivity.this, imageUrls, parseObject.getObjectId());
                    previewSmallSlide.setAdapter(smallSizeAdapter);

                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//Tim cac shopping cart trong local db
                            ParseQuery.getQuery(SHOPPING_CART_TABLE)
                                    .fromLocalDatastore()
                                    .include(CART_PRODUCT_ID)
                                    .findInBackground(new FindCallback<ParseObject>() {
                                        @Override
                                        public void done(List<ParseObject> list, ParseException e) {
                                            boolean check = false;
                                            if (e == null) {
                                                for (int i = 0; i < list.size(); i++) {
                                                    if (list.get(i).getParseObject(CART_PRODUCT_ID).getObjectId().equals(parseObject.getObjectId()))
                                                        check = true;
                                                }
                                            }
                                            if (check) {
                                                showDialog();
                                            } else {
                                                ParseObject cart = new ParseObject(SHOPPING_CART_TABLE);
                                                cart.put(CART_PRODUCT_ID, parseObject);
                                                cart.put(SHIP_QUANTITY, 1);
                                                //Neu nguoi dung co tai khoan thi moi set ACL.
                                                if (ParseUser.getCurrentUser() != null) {
                                                    cart.setACL(new ParseACL(ParseUser.getCurrentUser()));
                                                }
                                                cart.pinInBackground();
                                                Toast.makeText(getBaseContext(), getString(R.string.addToCartBtn), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                    });
                    viewSwitcher.showNext();
                } else {
                    e.printStackTrace();
                }
            }
        });

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Already in cart!");
        builder.setMessage("Do you want go to cart?");
        builder.setIcon(R.drawable.ic_info_black_48dp);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });
        builder.show();
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
