package com.project.mobileonline.activities;

import android.os.Bundle;
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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.PreviewSmallSizeAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.SetColoStatusBar;

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
import static com.project.mobileonline.models.Constants.OS;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_QUANTITY;
import static com.project.mobileonline.models.Constants.RAM;
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
    TextView detailPrice, detailProductQuantity, screenSpec, frontCameraSpec, backCameraSpec, osSpec,
            graphicSpec, cpuSpec, ramSpec, internalSpec, sdcardSpec, simSpec, batterySpec, connectSpec;
    RatingBar ratingBar;
    Button addToCart;

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

        backgroundProduct = (ImageView) findViewById(R.id.backgroundProduct);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
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
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        addToCart = (Button) findViewById(R.id.addToCart);

        previewSmallSlide = (RecyclerView) findViewById(R.id.previewProductSmallSlide);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        previewSmallSlide.setLayoutManager(layoutManager);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.PRODUCT_TABLE);
        query.getInBackground(getIntent().getStringExtra("productId"), new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject parseObject, ParseException e) {
                if (e == null) {
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
                    detailPrice.setText(parseObject.getNumber(PRODUCT_PRICE).toString());
                    detailProductQuantity.setText(getString(R.string.quantity) + parseObject.getNumber(PRODUCT_QUANTITY));
                    ImageLoader.getInstance().displayImage(parseObject.getString(BACKGROUND_IMAGE), backgroundProduct);
                    List<String> imageUrls = parseObject.getList(SMALL_SLIDE_IMAGE);
                    smallSizeAdapter = new PreviewSmallSizeAdapter(ProductDetailActivity.this, imageUrls, parseObject.getObjectId());
                    previewSmallSlide.setAdapter(smallSizeAdapter);

                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ParseObject cart = new ParseObject(SHOPPING_CART_TABLE);
                            cart.put(CART_PRODUCT_ID, parseObject);
                            cart.put(SHIP_QUANTITY, 1);
                            MainActivity.carts.add(cart);
                            Toast.makeText(getBaseContext(), "Added in cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

}
