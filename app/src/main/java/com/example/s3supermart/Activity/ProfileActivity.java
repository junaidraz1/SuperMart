package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout layout_back, layout_menu;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        layout_back = findViewById(R.id.ll_back);
        layout_menu = findViewById(R.id.ll_menu);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText("Profile");

        clickListeners();
    }

    public void clickListeners() {
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        layout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHandler.homeMenu(ProfileActivity.this);
            }
        });

    }
}