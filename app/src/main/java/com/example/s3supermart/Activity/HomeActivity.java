package com.example.s3supermart.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3supermart.Adapter.AllCategoriesAdapter;
import com.example.s3supermart.Fragment.HomeFragment;
import com.example.s3supermart.Fragment.MilkAndMilkPowderFragment;
import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.Fragment.WalletFragment;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Model.AllCategoriesClass;
import com.example.s3supermart.R;
import com.example.s3supermart.Utils.Utility;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab_viewCart;
    TextView tv_location;
    ImageView iv_menu;
    int backStack;
    Fragment HomeFrag, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFrag = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                HomeFrag.getClass().getSimpleName()).addToBackStack("fragment").commit();
        Log.d("FRAGMENTTEST", "back stack val on create: " + backStack);

        currentFragment = HomeFrag;
        //intialising ids to variables
        bottomNavigationView = findViewById(R.id.bottomnavView);
        fab_viewCart = findViewById(R.id.fab_viewCart);
        iv_menu = findViewById(R.id.iv_homeMenu);
        tv_location = findViewById(R.id.tv_location);

        bottomNavigationView.setBackground(null);

        tv_location.setSelected(true);
        tv_location.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        //method that contains implementation of click listeners
        clickListeners();

    }

    //implementation of all the click listeners
    public void clickListeners() {

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHandler.homeMenu(HomeActivity.this);
            }
        });

        fab_viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewCartActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.homePage:
                        Fragment fragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment,
                                fragment.getClass().getSimpleName()).commit();
                        currentFragment = fragment;
                        break;

                    case R.id.wallet:
                        Fragment fragment1 = new WalletFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1,
                                fragment1.getClass().getSimpleName()).commit();
                        currentFragment = fragment1;
                        break;

                    case R.id.myorders:
                        Fragment fragment3 = new MilkCheeseAndYogurtFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3,
                                fragment3.getClass().getSimpleName()).commit();
                        currentFragment = fragment3;
                        break;

                    case R.id.search:
                        Fragment fragment4 = new MilkAndMilkPowderFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment4,
                                fragment4.getClass().getSimpleName()).commit();
                        currentFragment = fragment4;
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (currentFragment.equals(HomeFrag)) {
            finishAffinity();

        } else {
            currentFragment = HomeFrag;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                    HomeFrag.getClass().getSimpleName()).commit();
            bottomNavigationView.getMenu().getItem(0).setChecked(true);

        }

        //gets value of fragments that are pushed to back stack
       /* backStack = getSupportFragmentManager().getBackStackEntryCount();


        if (backStack >= 2) {

            //pops fragments from backstack
            getSupportFragmentManager().popBackStack();
            Log.d("FRAGMENTTEST", "onBackPressed: Inside if");
            Log.d("FRAGMENTTEST", "back stack: " + backStack);

        } else {
            finishAffinity();
            Log.d("FRAGMENTTEST", "onBackPressed: Inside else");
            Log.d("FRAGMENTTEST", "back stack: " + backStack);
        } */

    }
}