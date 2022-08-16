package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.biometric.BiometricManager;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout layout_back, layout_menu;
    TextView tv_title;
    CardView cv_changePassword;
    SwitchCompat switch_fingerprintbtn;
    PrefsManager prefsManager;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        layout_back = findViewById(R.id.ll_back);
        layout_menu = findViewById(R.id.ll_menu);
        tv_title = findViewById(R.id.tv_title);
        cv_changePassword = findViewById(R.id.cv_changePassword);
        switch_fingerprintbtn = findViewById(R.id.btn_fingerprint);
        bottomNavigationView = findViewById(R.id.bottomnavView);

        tv_title.setText("Settings");
        bottomNavigationView.setBackground(null);

        prefsManager = new PrefsManager(SettingsActivity.this);

        //to check if biometric login was enabled previously to maintain its state
        biometricState();

        //implementation of click listeners involved
        clickListeners();

    }

    public void biometricState() {

        if (prefsManager.getFINGERPRINT_ENABLED() && checkbiometricSupport()) {

            switch_fingerprintbtn.setChecked(true);
        } else {
            switch_fingerprintbtn.setChecked(false);
        }

    }

    public void clickListeners() {
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        layout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHandler.homeMenu(SettingsActivity.this);
            }
        });

        switch_fingerprintbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                //check its state
                //if it is checked
                if (checked) {

                    //then check for biometric support
                    // if it is true
                    if (checkbiometricSupport()) {

                        //set button to check and set fingerprint status to true in pref
                        switch_fingerprintbtn.setChecked(true);
                        prefsManager.setFINGERPRINT_ENABLED(true);
                    } else {

                        //else set both to false
                        switch_fingerprintbtn.setChecked(false);
                        prefsManager.setFINGERPRINT_ENABLED(false);
                    }
                } else {

                    //if button is not checked then let it stay false and its state in pref manager false too
                    switch_fingerprintbtn.setChecked(false);
                    prefsManager.setFINGERPRINT_ENABLED(false);
                }
            }
        });

        cv_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, ChangePasswordActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    //method that checks if device supports biometric or not
    public boolean checkbiometricSupport() {
        boolean isChecked = false;
        BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            // this means we can use biometric sensor
            case BiometricManager.BIOMETRIC_SUCCESS:
                if (!prefsManager.getFINGERPRINT_ENABLED()) {
                    DialogHandler.customDialog(SettingsActivity.this, "Settings",
                            "Biometric Login Enabled successfully");
                }
                isChecked = true;
                break;

            // this means that the device doesn't have fingerprint sensor
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                DialogHandler.customDialog(SettingsActivity.this, "Settings",
                        "This device doesn't support biometric");
                break;

            // this means that biometric sensor is not available
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                DialogHandler.customDialog(SettingsActivity.this, "Settings",
                        "Biometric sensor unavailable");
                break;

            // this means that the device doesn't contain your fingerprint
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                DialogHandler.dialogbiometricSetting(SettingsActivity.this, "Settings",
                        "No saved fingerprint found, please set it from settings");
                break;
        }
        return isChecked;
    }
}