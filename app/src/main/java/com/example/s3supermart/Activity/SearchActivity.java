package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.s3supermart.R;

public class SearchActivity extends AppCompatActivity {

    TextView tv_topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tv_topBar = findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Search");
    }
}