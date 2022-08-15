package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3supermart.Adapter.AllCategoriesAdapter;
import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Model.AllCategoriesClass;
import com.example.s3supermart.R;
import com.example.s3supermart.Utils.Utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    CarouselView carouselView;
    BottomNavigationView bottomNavigationView;
    TextView tv_greetMsg, tv_location;
    RecyclerView rv_productCategory;
    ImageView iv_menu;
    AllCategoriesAdapter allCategoriesAdapter;
    Utility utility;
    int[] sampleImages = {R.drawable.offer1, R.drawable.offer2,
            R.drawable.logo, R.drawable.slider1, R.drawable.offer3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //intialising ids to variables
        carouselView = findViewById(R.id.carouselView);
        tv_greetMsg = findViewById(R.id.tv_gd_morning);
        bottomNavigationView = findViewById(R.id.bottomnavView);
        tv_location = findViewById(R.id.tv_location);
        rv_productCategory = findViewById(R.id.rv_productCategory);
        iv_menu = findViewById(R.id.iv_homeMenu);

        tv_location.setSelected(true);
        tv_location.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        bottomNavigationView.setBackground(null);

        //intialising classes
        utility = new Utility();

        //method that contains implementation of greeting message
        greetUser();

        //method that contains implementation of click listeners
        clickListeners();

        //to set items to recyclerview for product category
        setCategory();

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    //setting layout manager and adapter to category recycler view
    public void setCategory() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        allCategoriesAdapter = new AllCategoriesAdapter(this, categoryList());
        rv_productCategory.setAdapter(allCategoriesAdapter);
        rv_productCategory.setLayoutManager(layoutManager);
    }

    //implementation of all the click listeners
    public void clickListeners() {

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHandler.homeMenu(HomeActivity.this);
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

    //method to add items to category recycler view
    public List<AllCategoriesClass> categoryList() {

        List<AllCategoriesClass> allCategoriesList = new ArrayList<>();

        AllCategoriesClass listItem1 = new AllCategoriesClass("Dairy Product", R.drawable.olpers_milk_1_litre);
        AllCategoriesClass listItem2 = new AllCategoriesClass("Fruits and Vegetables", R.drawable.karaila);
        AllCategoriesClass listItem3 = new AllCategoriesClass("Noodles and Sauces", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem4 = new AllCategoriesClass("Snacks", R.drawable.lays);
        AllCategoriesClass listItem5 = new AllCategoriesClass("Ice Cream & Chocolates", R.drawable.pancake_serup);
        AllCategoriesClass listItem6 = new AllCategoriesClass("Beverages", R.drawable.pancake_serup);
        AllCategoriesClass listItem7 = new AllCategoriesClass("Personal Care", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem8 = new AllCategoriesClass("Baby Care", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem9 = new AllCategoriesClass("Oil and Ghee", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem10 = new AllCategoriesClass("Atta, Daal aur Chawal", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem11 = new AllCategoriesClass("Laundry", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem12 = new AllCategoriesClass("Tea, Coffee, Sugar", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem13 = new AllCategoriesClass("Baby Food", R.drawable.milk_cheese_and_yogurt);

        allCategoriesList.add(listItem1);
        allCategoriesList.add(listItem2);
        allCategoriesList.add(listItem3);
        allCategoriesList.add(listItem4);
        allCategoriesList.add(listItem5);
        allCategoriesList.add(listItem6);
        allCategoriesList.add(listItem7);
        allCategoriesList.add(listItem8);
        allCategoriesList.add(listItem9);
        allCategoriesList.add(listItem10);
        allCategoriesList.add(listItem11);
        allCategoriesList.add(listItem12);
        allCategoriesList.add(listItem13);

        return allCategoriesList;
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