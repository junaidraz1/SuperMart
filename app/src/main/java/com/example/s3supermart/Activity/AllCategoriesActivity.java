package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.R;

public class AllCategoriesActivity extends AppCompatActivity {


    LinearLayout ll_viewAll_MilkCheeseAnYogurt;
    AllCategoriesActivity allCategoriesActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        allCategoriesActivity = new AllCategoriesActivity();

        ll_viewAll_MilkCheeseAnYogurt = findViewById(R.id.ll_viewAll_MilkCheeseAnYogurt);

        ll_viewAll_MilkCheeseAnYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategoriesActivity.this, MilkCheeseAndYogurtActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}