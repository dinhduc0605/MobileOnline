package com.project.mobileonline.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.project.mobileonline.R;
import com.project.mobileonline.utils.LoadingDialog;

import static com.project.mobileonline.activities.LoadingActivity.login;

/**
 * Created by Nguyen Dinh Duc on 9/14/2015.
 */
public class SignInActivity extends AppCompatActivity {
    EditText usernameEdit, passwordEdit;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        initView();
    }

    private void initView() {
        usernameEdit = (EditText) findViewById(R.id.usernameEditSignIn);
        passwordEdit = (EditText) findViewById(R.id.passwordEditSignIn);
    }

    public void signIn(View view) {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();

        ParseUser.logInInBackground(usernameEdit.getText().toString(), passwordEdit.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    login = true;
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (e.getCode() == 101) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast
                                .LENGTH_SHORT)
                                .show();
                    }

                }
                loadingDialog.cancel();
            }
        });
    }

    public void register(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
