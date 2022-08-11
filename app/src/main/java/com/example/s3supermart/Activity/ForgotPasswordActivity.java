package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText et_resetEmail;
    Button btn_sendPass;
    RelativeLayout rl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //intialising ids
        et_resetEmail = findViewById(R.id.et_resetEmail);
        btn_sendPass = findViewById(R.id.btn_resetPassword);
        rl_back = findViewById(R.id.rl_back);

        //method contains implementation of click listeners involve
        clickListeners();
    }

    public void clickListeners() {

        //contains implementation of functionality when user clicks send password button
        btn_sendPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {

                    //to display password sent dialog
                    DialogHandler.customDialog(ForgotPasswordActivity.this, "Forgot Password",
                            "Password Reset Link has been sent to test123@gmail.com");
                }
            }
        });

        //to take user back to login page if he taps on back arrow image view
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    //method to validate fields for forgot password
    public boolean validation() {

        String resetEmail = et_resetEmail.getText().toString();

        if (resetEmail.equals("")) {
            DialogHandler.customDialog(ForgotPasswordActivity.this, "Reset Password", "Email is required");
            return false;
        } else {
            return true;
        }

    }
}