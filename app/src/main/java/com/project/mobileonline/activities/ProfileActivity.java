package com.project.mobileonline.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
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
    EditText email, phone, address, firstName, lastName, birthday, gender;
    ParseUser currentUser;
    boolean enable = false;

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

        email = (EditText) findViewById(R.id.email_profile);
        phone = (EditText) findViewById(R.id.phone_profile);
        address = (EditText) findViewById(R.id.address_profile);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        birthday = (EditText) findViewById(R.id.birthday_profile);
        gender = (EditText) findViewById(R.id.gender_profile);

        ParseUser currentUser = ParseUser.getCurrentUser();
        email.setText(currentUser.getString(EMAIL));
        phone.setText(currentUser.getString(PHONE));
        address.setText(currentUser.getString(ADDRESS));
        firstName.setText(currentUser.getString(FIRSTNAME));
        lastName.setText(currentUser.getString(LASTNAME));
//        birthday.setText(HelperClass.changeDateFormat(currentUser.getDate(BIRTHDAY)));
        gender.setText(currentUser.getBoolean(GENDER) ? "Male" : "Female");
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
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent intent = getIntent();
                intent.putExtra("filepath", filePath);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.editProfile:
                enableOrDisable(email);
                enableOrDisable(phone);
                enableOrDisable(address);
                enableOrDisable(firstName);
                enableOrDisable(lastName);
                enableOrDisable(birthday);
                enableOrDisable(gender);
                if (enable) {
                    enable = false;
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_mode_edit_white_24dp));
                    currentUser.put(EMAIL, email.getText().toString());
                    currentUser.put(PHONE, phone.getText().toString());
                    currentUser.put(ADDRESS, address.getText().toString());
                    currentUser.put(FIRSTNAME, firstName.getText().toString());
                    currentUser.put(LASTNAME, lastName.getText().toString());
//                    currentUser.put(BIRTHDAY, birthday.getText());
                    currentUser.put(GENDER, gender.getText().toString().equals("Male"));
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getBaseContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    enable = true;
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_done_white_24dp));
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void enableOrDisable(EditText editText) {
        if (enable) {
            editText.setEnabled(false);
        } else {
            editText.setEnabled(true);
        }
    }
}
