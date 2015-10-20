package com.project.mobileonline.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.project.mobileonline.R;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.HelperClass;
import com.project.mobileonline.utils.SetColoStatusBar;

import java.io.IOException;

import static com.project.mobileonline.models.Constants.ADDRESS;
import static com.project.mobileonline.models.Constants.AVATAR_IMAGE_NAME;
import static com.project.mobileonline.models.Constants.BIRTHDAY;
import static com.project.mobileonline.models.Constants.EMAIL;
import static com.project.mobileonline.models.Constants.FIRSTNAME;
import static com.project.mobileonline.models.Constants.GENDER;
import static com.project.mobileonline.models.Constants.LASTNAME;
import static com.project.mobileonline.models.Constants.PHONE;
import static com.project.mobileonline.models.Constants.USERNAME;

/**
 * Created by Nguyen Dinh Duc on 8/30/2015.
 */
public class ProfileActivity extends AppCompatActivity {
    private String filePath;
    ActionBar actionBar;
    ImageView avatar;
    String avatarPath;
    TextView email, phone, address, firstName, lastName, birthday, gender;
    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SetColoStatusBar.setColor(this);
        initView();
        getWidgetControl();
    }

    private void initView() {
        currentUser = ParseUser.getCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        avatar = (ImageView) findViewById(R.id.avatar_icon);
        avatarPath = Constants.DIRECTORY_PATH + currentUser.getString(USERNAME) + "/" + AVATAR_IMAGE_NAME;
        Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
        if (bitmap != null) {
            avatar.setImageBitmap(bitmap);
        }

        email = (TextView) findViewById(R.id.email_profile);
        phone = (TextView) findViewById(R.id.phone_profile);
        address = (TextView) findViewById(R.id.address_profile);
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        birthday = (TextView) findViewById(R.id.birthday_profile);
        gender = (TextView) findViewById(R.id.gender_profile);

        ParseUser currentUser = ParseUser.getCurrentUser();
        email.setText(currentUser.getString(EMAIL));
        phone.setText(currentUser.getString(PHONE));
        address.setText(currentUser.getString(ADDRESS));
        firstName.setText(currentUser.getString(FIRSTNAME));
        lastName.setText(currentUser.getString(LASTNAME));
        birthday.setText(currentUser.getString(BIRTHDAY));
        gender.setText(currentUser.getString(GENDER));
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
