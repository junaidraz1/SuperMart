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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.MintGreen));
        }

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
         /*       AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.dialog_error_message, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();*/
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