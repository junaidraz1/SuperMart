package com.example.s3supermart.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String APP_KEY = "AppKey";
    public String LOGIN_KEY = "KEY_LOGIN";
    public String FIRSTNAME_KEY = "KEY_FIRSTNAME";
    public String LASTNAME_KEY = "KEY_LASTNAME";
    public String USERMAIL_KEY = "KEY_USEREMAIL";
    public String Address_KEY = "KEY_LOCATION";
    public String MOBILENO_KEY = "KEY_MOBILENO";
    public String PASSWORD_KEY = "KEY_PASSWORD";
    public String CONFIRMPASS_KEY = "KEY_CONFIRMPASS";

    //  public String LOGIN_CLASS_KEY = "KEY_LOGIN_CLASS";
    public String FINGERPRINT_ENABLED = "KEY_FINGERPRINT";

    public PrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_KEY, 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLOGIN_KEY(boolean login_key) {
        editor.putBoolean(LOGIN_KEY, login_key);
        editor.commit();
    }

    public boolean getLogin() {
        return sharedPreferences.getBoolean(LOGIN_KEY, false);
    }

    public void setFIRSTNAME_KEY(String firstName) {
        editor.putString(FIRSTNAME_KEY, firstName);
        editor.commit();
    }

    public String getFIRSTNAME_KEY() {
        return sharedPreferences.getString(FIRSTNAME_KEY, "");
    }

    public void setLASTNAME_KEY(String lastName) {
        editor.putString(LASTNAME_KEY, lastName);
        editor.commit();
    }

    public String getLASTNAME_KEY() {
        return sharedPreferences.getString(LASTNAME_KEY, "");
    }

    public void setUSERMAIL_KEY(String email) {
        editor.putString(USERMAIL_KEY, email);
        editor.commit();
    }

    public String getUSERMAIL_KEY() {
        return sharedPreferences.getString(USERMAIL_KEY, "");
    }

    public void setPASSWORD_KEY(String password) {
        editor.putString(PASSWORD_KEY, password);
        editor.commit();
    }

    public String getPASSWORD_KEY() {
        return sharedPreferences.getString(PASSWORD_KEY, "");
    }

    public void setFINGERPRINT_ENABLED(boolean fingerprint_enabled) {
        editor.putBoolean(FINGERPRINT_ENABLED, fingerprint_enabled);
        editor.commit();
    }

    public boolean getFINGERPRINT_ENABLED() {
        return sharedPreferences.getBoolean(FINGERPRINT_ENABLED, false);
    }

    public void setAddress_KEY(String currentLocation) {
        editor.putString(Address_KEY, currentLocation);
        editor.commit();
    }

    public String getAddress_KEY() {
        return sharedPreferences.getString(Address_KEY, "");
    }

    public void setMOBILENO_KEY(String mobileNo) {
        editor.putString(MOBILENO_KEY, mobileNo);
        editor.commit();
    }

    public String getMOBILENO_KEY() {
        return sharedPreferences.getString(MOBILENO_KEY, "");
    }

    public void setCONFIRMPASS_KEY(String appartmentInfo) {
        editor.putString(CONFIRMPASS_KEY, appartmentInfo);
        editor.commit();
    }

    public String getCONFIRMPASS_KEY() {
        return sharedPreferences.getString(CONFIRMPASS_KEY, "");
    }
}
