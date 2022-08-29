package com.example.s3supermart.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;
import com.xwray.passwordview.PasswordView;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";

    RelativeLayout rl_forgotPassword, rl_signUp;
    Button btn_login;
    EditText et_email;
    PasswordView pv_password;
    ImageView iv_fingerprint;
    String email, password;
    PrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //intialising ids
        rl_forgotPassword = findViewById(R.id.rl_forgotPass);
        rl_signUp = findViewById(R.id.rl_signUp);
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_email);
        pv_password = findViewById(R.id.pv_password);
        iv_fingerprint = findViewById(R.id.iv_fingerprint);

        iv_fingerprint.setVisibility(View.GONE);

        //intialising classes
        prefsManager = new PrefsManager(LoginActivity.this);

        //click listener implementation method
        clickListeners();

        //to check if biometric is enabled in order to show fingerprint image view
        if (prefsManager.getFINGERPRINT_ENABLED()) {
            iv_fingerprint.setVisibility(View.VISIBLE);
        }
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
                    prefsManager.setLOGIN_KEY(true);
                    prefsManager.setUSERMAIL_KEY(email);
                    prefsManager.setPASSWORD_KEY(password);

                    startActivity(new Intent(LoginActivity.this, GoogleMapActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

        iv_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorizeFingerprint();
            }
        });
    }

    public void authorizeFingerprint() {

        // creating a variable for our Executor
        Executor executor = ContextCompat.getMainExecutor(this);

        // this will give us result of AUTHENTICATION
        final BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.d(TAG, "Biometric Authentication Error: " + errorCode + ":" + errString);
            }

            // THIS METHOD IS CALLED WHEN AUTHENTICATION IS SUCCESS
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                email = prefsManager.getUSERMAIL_KEY();
                password = prefsManager.getPASSWORD_KEY();
                Log.d(TAG, "onAuthenticationSucceeded: USERNAME: " + email);
                Log.d(TAG, "onAuthenticationSucceeded: PASS: " + password);
                prefsManager.setLOGIN_KEY(true);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Biometric Authentication Failed");
            }
        });

        // creating a variable for our promptInfo BIOMETRIC DIALOG
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Fingerprint Login")
                .setDescription("Use your fingerprint to login ").setNegativeButtonText("Cancel").build();
        iv_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }

    //method to validate fields for login
    public boolean validation() {

        email = et_email.getText().toString();
        password = pv_password.getText().toString();

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