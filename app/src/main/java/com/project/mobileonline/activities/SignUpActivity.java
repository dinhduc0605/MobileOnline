package com.project.mobileonline.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.project.mobileonline.R;

public class SignUpActivity extends AppCompatActivity {
    EditText passwordEdit, usernameEdit, emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getBaseContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.w("crash", e.getCode() + "");
                }
            }
        });
    }
}
