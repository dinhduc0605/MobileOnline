package com.project.mobileonline.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.project.mobileonline.R;
import com.project.mobileonline.utils.SetColoStatusBar;

public class AboutActivity extends AppCompatActivity {
    ImageView mapView;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setSupportActionBar((Toolbar) findViewById(R.id.actionbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SetColoStatusBar.setColor(this);

        mapView = (ImageView) findViewById(R.id.mapview);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:21.0070516,105.84289560000002"));
                startActivity(intent);
            }
        });
//        mapView.onCreate(savedInstanceState);
//        googleMap = mapView.getMap();
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(21.0070516, 105.84289560000002)));
//        MapsInitializer.initialize(this);
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(21.0070516, 105.84289560000002), 15);
//        googleMap.moveCamera(cameraUpdate);
//        googleMap.getUiSettings().setScrollGesturesEnabled(false);
//        googleMap.getUiSettings().setZoomGesturesEnabled(false);
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:21.0070516,105.84289560000002"));
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_global, menu);
        return true;
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
}
