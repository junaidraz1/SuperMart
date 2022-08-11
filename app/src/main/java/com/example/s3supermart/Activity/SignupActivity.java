package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.s3supermart.R;

public class SignupActivity extends AppCompatActivity {

    EditText et_fname, et_lname, et_email, et_password, et_confirmPass;
    Button btn_regUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //intialising ids
        et_fname = findViewById(R.id.et_fName);
        et_lname = findViewById(R.id.et_lName);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmPass = findViewById(R.id.et_confirmPassword);
        btn_regUser = findViewById(R.id.btn_regUser);

        //contains implementation of all the click listeners involve
        clickListeners();
    }

    public void clickListeners() {

        //contains implementation of functionality when user clicks on register button
        btn_regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}