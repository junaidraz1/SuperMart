package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.s3supermart.Adapter.ViewPagerAdapter;
import com.example.s3supermart.Fragment.CheeseAndCreamFragment;
import com.example.s3supermart.Fragment.FlavouredMilkFragment;
import com.example.s3supermart.Fragment.MilkAndMilkPowderFragment;
import com.example.s3supermart.R;
import com.google.android.material.tabs.TabLayout;

public class MilkCheeseAndYogurtActivity extends AppCompatActivity {
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    LinearLayout layout_back,layout_menu;
    RelativeLayout layout_homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_cheese_and_yogurt);

        //intialising ids to  variables
        viewPager = findViewById(R.id.viewpagerAddPatient);
        tabLayout = findViewById(R.id.tab_layoutAddPatient);
        layout_back = findViewById(R.id.ll_backAddPatient);
        layout_homeBtn = findViewById(R.id.Rl_homeBtn);
        layout_menu = findViewById(R.id.ll_menuAddPatient);

      /*  //method that contains click listener implementation
        clickListeners();
*/
        //this is used to store memory of tabs that aren't active on screen
        //store memory means if user swipes from one tab to another it will store the filled state of fields
        //limit 5 means 5 tabs next to the current one
        viewPager.setOffscreenPageLimit(5);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // adding fragments into viewpager
        viewPagerAdapter.add(new MilkAndMilkPowderFragment(), "Milk & Milk Powder");
        viewPagerAdapter.add(new FlavouredMilkFragment(), "Flavoured Milk");
        viewPagerAdapter.add(new CheeseAndCreamFragment(), "Cheese & Cream");
       /* viewPagerAdapter.add(new VaccinationDetailFragment(), "Vaccination Details");
        viewPagerAdapter.add(new NextOfKinFragment(), "Next of Kin");
        viewPagerAdapter.add(new RegistrationFeeFragment(), "Registration fee");*/

        //setting adapter to viewpager
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}