package com.project.mobileonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.project.mobileonline.R;

/**
 * Created by Nguyen Dinh Duc on 9/14/2015.
 */
public class LoginAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.login = true;
        startActivity(intent);
        finish();
    }
}
