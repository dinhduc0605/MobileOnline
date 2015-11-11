package com.project.mobileonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;
import com.project.mobileonline.R;

public class LoadingActivity extends AppCompatActivity {
    static boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ParseUser user = ParseUser.getCurrentUser();
        if (user == null) {
            login = false;
        } else {
            login = true;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
