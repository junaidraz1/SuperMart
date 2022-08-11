package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.s3supermart.R;

public class LoginActivity extends AppCompatActivity {

    TextView tv_forgotPassword, tv_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //intialising ids
        tv_forgotPassword = findViewById(R.id.tv_forgotPass);
        tv_signUp = findViewById(R.id.tv_signUp);

        clickListeners();
    }

    //method that contains implementation of all click listeners
    public void clickListeners() {

        //implementation of forgot password functionality
        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to start forgot password activity
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //implementation of signup functionality
        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to start sign up activity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });


    }
}