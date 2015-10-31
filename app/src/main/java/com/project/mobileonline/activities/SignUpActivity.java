package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.project.mobileonline.R;
import com.project.mobileonline.utils.LoadingDialog;

public class SignUpActivity extends AppCompatActivity {
    EditText passwordEdit, usernameEdit, emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
        usernameEdit = (EditText) findViewById(R.id.usernameEditSignUp);
        passwordEdit = (EditText) findViewById(R.id.passwordEditSignUp);
        emailEdit = (EditText) findViewById(R.id.emailEditSignUp);
    }

    public void signUp(View view) {
        ParseUser user = new ParseUser();
        user.setUsername(usernameEdit.getText().toString());
        user.setPassword(passwordEdit.getText().toString());
        user.setEmail(emailEdit.getText().toString());
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getBaseContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    switch (e.getCode()) {
                        case -1:
                            Toast.makeText(getBaseContext(), "username or password is missing",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 202:
                        case 203:
                        case 125:
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                            break;
                    }

                }
                loadingDialog.cancel();
            }
        });
    }
}
