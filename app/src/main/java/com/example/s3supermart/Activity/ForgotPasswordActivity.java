package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.s3supermart.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText et_resetEmail;
    Button btn_sendPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //intialising ids
        et_resetEmail = findViewById(R.id.et_resetEmail);
        btn_sendPass = findViewById(R.id.btn_resetPassword);

        //method contains implementation of click listeners involve
        clickListeners();
    }

    public void clickListeners() {

        //contains implementation of functionality when user clicks send password button
        btn_sendPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}