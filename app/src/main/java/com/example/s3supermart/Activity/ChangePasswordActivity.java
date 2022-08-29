package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;

public class ChangePasswordActivity extends AppCompatActivity {

    LinearLayout layout_back, layout_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //intialising id's to variables
        layout_back = findViewById(R.id.ll_back);
        layout_menu = findViewById(R.id.ll_menu);

        //method that contain click implementations
        clickListeners();
    }

    //click listeners functionality defined here
    public void clickListeners() {

        //when user clicks on back image
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangePasswordActivity.this, HomeActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //when user clicks on menu image
        layout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHandler.homeMenu(ChangePasswordActivity.this);
            }
        });
    }
}