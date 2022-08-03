package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeActivity extends AppCompatActivity {

    CarouselView carouselView;
    LinearLayout ll_viewAll;
    CardView cv_milkCheeseAndYogurt;
    int[] sampleImages = {R.drawable.offer1, R.drawable.offer2, R.drawable.logo, R.drawable.slider1, R.drawable.offer3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //intialising ids to variables
        carouselView = findViewById(R.id.carouselView);
        ll_viewAll = findViewById(R.id.ll_viewAll);
        cv_milkCheeseAndYogurt = findViewById(R.id.cv_milkCheeseAndYogurt);


        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        ll_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllCategoriesActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        cv_milkCheeseAndYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MilkCheeseAndYogurtActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
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