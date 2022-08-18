package com.example.s3supermart.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.biometric.BiometricManager;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.s3supermart.Activity.ChangePasswordActivity;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;

public class SettingsFragment extends Fragment {

    CardView cv_changePassword;
    SwitchCompat switch_fingerprintbtn;
    PrefsManager prefsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        cv_changePassword = view.findViewById(R.id.cv_changePassword);
        switch_fingerprintbtn = view.findViewById(R.id.btn_fingerprint);

        //to check if biometric login was enabled previously to maintain its state
        biometricState();

        //implementation of click listeners involved
        clickListeners();

        return view;
    }

    public void biometricState() {

        if (getContext() != null) {
            prefsManager = new PrefsManager(getContext());
            if (prefsManager.getFINGERPRINT_ENABLED() && checkbiometricSupport()) {

                switch_fingerprintbtn.setChecked(true);
            } else {
                switch_fingerprintbtn.setChecked(false);
            }
        }
    }

    public void clickListeners() {

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
                if (getContext() != null) {
                    startActivity(new Intent(getContext(), ChangePasswordActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }

    //method that checks if device supports biometric or not
    public boolean checkbiometricSupport() {
        boolean isChecked = false;

        if (getContext() != null) {
            BiometricManager biometricManager = androidx.biometric.BiometricManager.from(getContext());
            switch (biometricManager.canAuthenticate()) {

                // this means we can use biometric sensor
                case BiometricManager.BIOMETRIC_SUCCESS:
                    if (!prefsManager.getFINGERPRINT_ENABLED()) {
                        DialogHandler.customDialog(getContext(), "Settings",
                                "Biometric Login Enabled successfully");
                    }
                    isChecked = true;
                    break;

                // this means that the device doesn't have fingerprint sensor
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    DialogHandler.customDialog(getContext(), "Settings",
                            "This device doesn't support biometric");
                    break;

                // this means that biometric sensor is not available
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    DialogHandler.customDialog(getContext(), "Settings",
                            "Biometric sensor unavailable");
                    break;

                // this means that the device doesn't contain your fingerprint
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    DialogHandler.dialogbiometricSetting(getContext(), "Settings",
                            "No saved fingerprint found, please set it from settings");
                    break;
            }
        }
        return isChecked;
    }
}