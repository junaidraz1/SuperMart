package com.example.s3supermart.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3supermart.Fragment.GroceryFragment;
import com.example.s3supermart.Fragment.HomeFragment;
import com.example.s3supermart.Fragment.MilkAndMilkPowderFragment;
import com.example.s3supermart.Fragment.MilkCheeseAndYogurtFragment;
import com.example.s3supermart.Fragment.WalletFragment;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab_viewCart;
    Fragment HomeFrag;
    LinearLayout ll_back, ll_menu;
    RelativeLayout rl_homeactTopbar;


    public static Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //intialising ids to variables


//        iv_location = findViewById(R.id.iv_location);


        bottomNavigationView = findViewById(R.id.bottomnavView);
        fab_viewCart = findViewById(R.id.fab_viewCart);
        rl_homeactTopbar = findViewById(R.id.rl_topbarHomeAct);
        ll_back = findViewById(R.id.ll_back);
        ll_menu = findViewById(R.id.ll_menu);

        bottomNavigationView.setBackground(null);

        //setting title and back icon gone on default
//        tv_title.setVisibility(View.GONE);
//        iv_back.setVisibility(View.GONE);

        //to load home fragment when activity is created
        loadFrag();

        //method that contains implementation of click listeners
        clickListeners();

    }

    public void changetopBar() {

        if (currentFragment != HomeFrag) {
            rl_homeactTopbar.setVisibility(View.VISIBLE);
        }
    }

    public void loadFrag() {
        HomeFrag = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                HomeFrag.getClass().getSimpleName()).commit();

        currentFragment = HomeFrag;
    }

    //implementation of all the click listeners
    public void clickListeners() {

        fab_viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewCartActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //when back icon is clicked from the fragments other then home frag
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    /* when user opens home fragment, title and back button will be set to gone
                    in order to display his location on home */
                    case R.id.homePage:
                        Fragment fragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment,
                                fragment.getClass().getSimpleName()).commit();

                        rl_homeactTopbar.setVisibility(View.GONE);
                        currentFragment = fragment;
                        break;

                        /* when user opens wallet fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside wallet fragment */
                    case R.id.wallet:
                        Fragment fragment1 = new WalletFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1,
                                fragment1.getClass().getSimpleName()).commit();

                        rl_homeactTopbar.setVisibility(View.VISIBLE);
                        currentFragment = fragment1;
                        break;

                        /* when user opens myorders fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside myorders fragment */
                    case R.id.myorders:
                        Fragment fragment3 = new MilkCheeseAndYogurtFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3,
                                fragment3.getClass().getSimpleName()).commit();

                        currentFragment = fragment3;
                        break;

                        /* when user opens search fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside search fragment */
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
            Log.d("HOMEAct", "onBackPressed: onback if working");
            finishAffinity();


        } else {
            Log.d("HOMEAct", "onBackPressed: onback else working");
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                    HomeFrag.getClass().getSimpleName()).commit();

            rl_homeactTopbar.setVisibility(View.GONE);
            currentFragment = HomeFrag;

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