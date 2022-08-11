package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;
import com.xwray.passwordview.PasswordView;

public class SignupActivity extends AppCompatActivity {

    EditText et_fname, et_lname, et_email;
    PasswordView pv_password, pv_confirmPass;
    Button btn_regUser;
    RelativeLayout rl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //intialising ids
        et_fname = findViewById(R.id.et_fName);
        et_lname = findViewById(R.id.et_lName);
        et_email = findViewById(R.id.et_email);
        pv_password = findViewById(R.id.pv_password);
        pv_confirmPass = findViewById(R.id.pv_confirmPassword);
        btn_regUser = findViewById(R.id.btn_regUser);
        rl_back = findViewById(R.id.rl_back);

        //contains implementation of all the click listeners involve
        clickListeners();
    }

    public void clickListeners() {

        //contains implementation of functionality when user clicks on register button
        btn_regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    DialogHandler.customDialog(SignupActivity.this, "Registration", "User Registered Successfully");
                }
            }
        });

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    //method to validate fields for signup
    public boolean validation() {

        String firstName = et_fname.getText().toString();
        String email = et_email.getText().toString();
        String password = pv_password.getText().toString();
        String con_pass = pv_confirmPass.getText().toString();

        if (firstName.equals("")) {
            et_fname.setError("First name is required");
            return false;

        } else if (email.equals("")) {
            et_email.setError("Email is required");
            return false;

        } else if (password.equals("")) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Password is required");
            return false;

        } else if (con_pass.equals("")) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Please confirm password");
            return false;

        } else if (!password.matches(con_pass)) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Password doesn't matches");
            return false;

        } else {
            return true;
        }

    }
}