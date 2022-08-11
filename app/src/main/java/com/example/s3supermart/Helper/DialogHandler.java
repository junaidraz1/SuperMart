package com.example.s3supermart.Helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

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

}
