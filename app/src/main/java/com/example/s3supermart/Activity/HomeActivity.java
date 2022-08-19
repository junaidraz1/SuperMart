package com.example.s3supermart.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.os.Handler;
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
import com.example.s3supermart.Fragment.ProfileFragment;
import com.example.s3supermart.Fragment.WalletFragment;
import com.example.s3supermart.Helper.AsyncTaskRunner;
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
    TextView tv_location, tv_title;
    ImageView iv_menu, iv_location, iv_edit, iv_back;
    Fragment HomeFrag;
    String req = "Calling Async";
    AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
    public static Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //intialising ids to variables
        bottomNavigationView = findViewById(R.id.bottomnavView);
        fab_viewCart = findViewById(R.id.fab_viewCart);
        iv_location = findViewById(R.id.iv_location);
        iv_menu = findViewById(R.id.iv_homeMenu);
        iv_edit = findViewById(R.id.iv_editLocation);
        iv_back = findViewById(R.id.iv_back);
        tv_location = findViewById(R.id.tv_location);
        tv_title = findViewById(R.id.tv_title);

        bottomNavigationView.setBackground(null);

        tv_location.setSelected(true);
        tv_location.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        //setting title and back icon gone on default
        tv_title.setVisibility(View.GONE);
        iv_back.setVisibility(View.GONE);

        // asyncTaskRunner.execute();

        //to load home fragment when activity is created
        loadFrag();

        //method that contains implementation of click listeners
        clickListeners();

       // runThread();

    }

    private void runThread() {
        runOnUiThread(new Thread(new Runnable() {
            public void run() {
                //to load home fragment when activity is created
                loadFrag();

                //method that contains implementation of click listeners
                clickListeners();
                try {
                    Thread.sleep(300);
                    Log.d("actHOME", "run: ");
                } catch (InterruptedException e) {
                    Log.d("actHOME", "run: " + e.getLocalizedMessage());
                }
            }
        }));
    }

    public void loadFrag() {
        HomeFrag = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                HomeFrag.getClass().getSimpleName()).addToBackStack("fragment").commit();

        currentFragment = HomeFrag;
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

        //when back icon is clicked from the fragments other then home frag
        iv_back.setOnClickListener(new View.OnClickListener() {
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
                        iv_location.setVisibility(View.VISIBLE);
                        tv_location.setVisibility(View.VISIBLE);
                        iv_edit.setVisibility(View.VISIBLE);
                        tv_title.setVisibility(View.GONE);
                        iv_back.setVisibility(View.GONE);
                        currentFragment = fragment;
                        break;

                        /* when user opens wallet fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside wallet fragment */
                    case R.id.wallet:
                        Fragment fragment1 = new WalletFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1,
                                fragment1.getClass().getSimpleName()).commit();
                        iv_location.setVisibility(View.GONE);
                        tv_location.setVisibility(View.GONE);
                        iv_edit.setVisibility(View.GONE);
                        tv_title.setVisibility(View.VISIBLE);
                        iv_back.setVisibility(View.VISIBLE);
                        tv_title.setText("Wallet");
                        currentFragment = fragment1;
                        break;

                        /* when user opens myorders fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside myorders fragment */
                    case R.id.myorders:
                        Fragment fragment3 = new MilkCheeseAndYogurtFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3,
                                fragment3.getClass().getSimpleName()).commit();
                        iv_location.setVisibility(View.GONE);
                        tv_location.setVisibility(View.GONE);
                        iv_edit.setVisibility(View.GONE);
                        tv_title.setVisibility(View.VISIBLE);
                        iv_back.setVisibility(View.VISIBLE);
                        tv_title.setText("My Orders");
                        currentFragment = fragment3;
                        break;

                        /* when user opens search fragment, location and edit location icon will be set to gone
                    in order to display tab title and back icon inside search fragment */
                    case R.id.search:
                        Fragment fragment4 = new MilkAndMilkPowderFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment4,
                                fragment4.getClass().getSimpleName()).commit();
                        iv_location.setVisibility(View.GONE);
                        tv_location.setVisibility(View.GONE);
                        iv_edit.setVisibility(View.GONE);
                        tv_title.setVisibility(View.VISIBLE);
                        iv_back.setVisibility(View.VISIBLE);
                        tv_title.setText("Search");
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
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HomeFrag,
                    HomeFrag.getClass().getSimpleName()).commit();
            iv_location.setVisibility(View.VISIBLE);
            tv_location.setVisibility(View.VISIBLE);
            iv_edit.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.GONE);
            iv_back.setVisibility(View.GONE);

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