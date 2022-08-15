package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.R;
import com.example.s3supermart.Utils.Utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeActivity extends AppCompatActivity {

    CarouselView carouselView;
    // LinearLayout ll_viewAll;
    BottomNavigationView bottomNavigationView;
    CardView cv_milkCheeseAndYogurt;
    TextView tv_greetMsg;
    Utility utility;
    int[] sampleImages = {R.drawable.offer1, R.drawable.offer2,
            R.drawable.logo, R.drawable.slider1, R.drawable.offer3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_2);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.MintGreen));
//        }

        //intialising ids to variables
        carouselView = findViewById(R.id.carouselView);
        // ll_viewAll = findViewById(R.id.ll_viewAll);
        cv_milkCheeseAndYogurt = findViewById(R.id.cv_milkCheeseAndYogurt);
        tv_greetMsg = findViewById(R.id.tv_gd_morning);
        bottomNavigationView = findViewById(R.id.bottomnavView);

        bottomNavigationView.setBackground(null);

        //intialising classes
        utility = new Utility();

        //method that contains implementation of greeting message
        greetUser();

        //method that contains implementation of click listeners
        clickListeners();

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    public void clickListeners() {

//                ll_viewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, AllCategoriesActivity.class).
//                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//            }
//        });
        cv_milkCheeseAndYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MilkCheeseAndYogurtActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    //method to display greeting message on home screen
    public void greetUser() {
        if (utility.getWishingMessage() == null) {
            tv_greetMsg.setText("Good Morning");

        } else {
            tv_greetMsg.setText(utility.getWishingMessage());
        }
    }

    //image listener for carousel view
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}