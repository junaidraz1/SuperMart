package com.example.s3supermart.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String APP_KEY = "AppKey";
    public String LOGIN_KEY = "KEY_LOGIN";
    public String USERMAIL_KEY = "KEY_USEREMAIL";
    public String PASSWORD_KEY = "KEY_PASSWORD";
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

}
