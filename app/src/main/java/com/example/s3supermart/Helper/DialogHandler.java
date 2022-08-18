package com.example.s3supermart.Helper;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import static com.example.s3supermart.Activity.HomeActivity.currentFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s3supermart.Activity.LoginActivity;
import com.example.s3supermart.Fragment.ProfileFragment;
import com.example.s3supermart.Fragment.SettingsFragment;
import com.example.s3supermart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DialogHandler {

    public static void customDialog(Context context, String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout layout_close;
        Button btn_ok;
        TextView tv_diagMessage, tv_diagTitle;
        tv_diagTitle = view.findViewById(R.id.tv_dialogTitle);
        tv_diagMessage = view.findViewById(R.id.tv_dialogMessage);
        btn_ok = view.findViewById(R.id.btn_dialogBtn);
        layout_close = view.findViewById(R.id.rl_closeIcon);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        layout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_diagMessage.setText(text);
        tv_diagTitle.setText(title);

        dialog.show();
    }

    public static void homeMenu(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dialog_home_menu, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        View view = new View(context);
        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.END, -15, 80);

        TextView tv_versionNum, tv_location, tv_title;
        LinearLayout layout_profile, layout_setting, layout_logout;
        ImageView iv_location, iv_edit, iv_back;
        BottomNavigationView bottomNavigationView;

        layout_profile = popupView.findViewById(R.id.ll_profile);
        layout_setting = popupView.findViewById(R.id.ll_settings);
        layout_logout = popupView.findViewById(R.id.ll_logout);
        tv_versionNum = popupView.findViewById(R.id.tv_versionNum);
        iv_location = ((AppCompatActivity) context).findViewById(R.id.iv_location);
        iv_edit = ((AppCompatActivity) context).findViewById(R.id.iv_editLocation);
        iv_back = ((AppCompatActivity) context).findViewById(R.id.iv_back);
        tv_location = ((AppCompatActivity) context).findViewById(R.id.tv_location);
        tv_title = ((AppCompatActivity) context).findViewById(R.id.tv_title);
        bottomNavigationView = ((AppCompatActivity) context).findViewById(R.id.bottomnavView);

        tv_versionNum.setText("1.0");
        bottomNavigationView.setBackground(null);

        layout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                currentFragment = new ProfileFragment();
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, currentFragment,
                        currentFragment.getClass().getSimpleName()).commit();
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                iv_location.setVisibility(View.GONE);
                iv_edit.setVisibility(View.GONE);
                tv_location.setVisibility(View.GONE);
                iv_back.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.VISIBLE);

                tv_title.setText("Profile");

            }
        });

        layout_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                currentFragment = new SettingsFragment();
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, currentFragment,
                        currentFragment.getClass().getSimpleName()).commit();
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                iv_location.setVisibility(View.GONE);
                iv_edit.setVisibility(View.GONE);
                tv_location.setVisibility(View.GONE);
                iv_back.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.VISIBLE);

                tv_title.setText("Settings");
            }
        });

        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                logoutDialog(context, "Logout", "Do you want to logout?");
            }
        });

    }

    public static void logoutDialog(Context context, String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_logout, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout layout_close;
        Button btn_yes, btn_no;
        TextView tv_diagMessage, tv_diagTitle;
        tv_diagTitle = view.findViewById(R.id.tv_dialogTitle);
        tv_diagMessage = view.findViewById(R.id.tv_dialogMessage);
        btn_yes = view.findViewById(R.id.btn_logoutYes);
        btn_no = view.findViewById(R.id.btn_logoutNo);
        layout_close = view.findViewById(R.id.rl_closeIcon);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                PrefsManager prefsManager = new PrefsManager(context);

                if (prefsManager.getFINGERPRINT_ENABLED()) {
                    prefsManager.editor.remove("KEY_LOGIN");
                    prefsManager.editor.commit();

                } else {
                    prefsManager.editor.clear();
                    prefsManager.editor.commit();
                }
                Intent intent = new Intent(context, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        layout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_diagMessage.setText(text);
        tv_diagTitle.setText(title);

        dialog.show();
    }

    //dialog that opens biometric settings of device when device supports fingerprint
    //but no fingerprint is configured
    public static void dialogbiometricSetting(Context context, String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout layout_close;
        Button btn_ok;
        TextView tv_errorMessage, tv_titlesettingsDialog;
        tv_errorMessage = view.findViewById(R.id.tv_dialogMessage);
        tv_titlesettingsDialog = view.findViewById(R.id.tv_dialogTitle);
        btn_ok = view.findViewById(R.id.btn_dialogBtn);
        layout_close = view.findViewById(R.id.rl_closeIcon);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                context.startActivity(new Intent(Settings.ACTION_BIOMETRIC_ENROLL));
            }
        });

        layout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_errorMessage.setText(text);
        tv_titlesettingsDialog.setText(title);

        dialog.show();
    }

}
