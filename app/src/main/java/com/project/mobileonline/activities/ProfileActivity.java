package com.project.mobileonline.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.project.mobileonline.R;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.HelperClass;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nguyen Dinh Duc on 8/30/2015.
 */
public class ProfileActivity extends AppCompatActivity {
    private String filePath;
    ActionBar actionBar;
    ImageView avatar;
    String avatarPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SystemBarTintManager manager = new SystemBarTintManager(this);
        manager.setStatusBarTintEnabled(true);
        manager.setTintResource(R.color.actionbar_bg);
        initView();
        getWidgetControl();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        avatar = (ImageView) findViewById(R.id.avatar_icon);
        avatarPath = Constants.DIRECTORY_PATH + "Nguyen Dinh Duc/" + Constants.AVATAR_IMAGE_NAME;
        Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
        if (bitmap != null) {
            avatar.setImageBitmap(bitmap);
        }
    }

    private void getWidgetControl() {
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture..."), Constants.PICK_IMAGE_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_CODE) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        avatar.setImageBitmap(bitmap);
                        Bitmap smallSizeBitmap = HelperClass.getBitmapSameSizeToView(avatar);
                        filePath = HelperClass.saveBitmapToFile(avatarPath, smallSizeBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = getIntent();
            intent.putExtra("filepath", filePath);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
