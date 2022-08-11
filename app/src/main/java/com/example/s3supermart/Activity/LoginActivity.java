package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;
import com.xwray.passwordview.PasswordView;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout rl_forgotPassword, rl_signUp;
    Button btn_login;
    EditText et_email;
    PasswordView pv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //intialising ids
        rl_forgotPassword = findViewById(R.id.rl_forgotPass);
        rl_signUp = findViewById(R.id.rl_signUp);
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_email);
        ;
        pv_password = findViewById(R.id.pv_password);

        clickListeners();
    }

    //method that contains implementation of all click listeners
    public void clickListeners() {

        //implementation of forgot password functionality
        rl_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to start forgot password activity
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //implementation of signup functionality
        rl_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to start sign up activity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

        //implementation of login functionality
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to check if validation is fullfilled and true
                if (validation()) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

    }

    //method to validate fields for login
    public boolean validation() {

        String email = et_email.getText().toString();
        String password = pv_password.getText().toString();

        if (email.equals("")) {
            DialogHandler.customDialog(LoginActivity.this, "Login", "Email is Required");
            return false;

        } else if (password.equals("")) {
            DialogHandler.customDialog(LoginActivity.this, "Login", "Password is Required");
            return false;

        } else {
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}