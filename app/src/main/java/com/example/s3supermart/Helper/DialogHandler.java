package com.example.s3supermart.Helper;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Activity.LoginActivity;
import com.example.s3supermart.Activity.ProfileActivity;
import com.example.s3supermart.Activity.SettingsActivity;
import com.example.s3supermart.R;

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

        TextView tv_profile, tv_settings, tv_logout, tv_versionNum;

        tv_profile = popupView.findViewById(R.id.tv_profile);
        tv_settings = popupView.findViewById(R.id.tv_settings);
        tv_logout = popupView.findViewById(R.id.tv_logout);
        tv_versionNum = popupView.findViewById(R.id.tv_versionNum);

        tv_versionNum.setText("1.0");

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                Intent intent = new Intent(context, ProfileActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        tv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                Intent intent = new Intent(context, SettingsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener() {
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
