package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3supermart.Adapter.ViewPagerAdapter;
import com.example.s3supermart.R;
import com.google.android.material.tabs.TabLayout;

public class MilkCheeseAndYogurtFragment extends Fragment {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    LinearLayout layout_back,layout_menu;
    RelativeLayout layout_homeBtn;
    TextView tv_topBar;


    public MilkCheeseAndYogurtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_milk_cheese_and_yogurt, container, false);

        //intialising ids to  variables
        viewPager = view.findViewById(R.id.viewpagerAddPatient);
        tabLayout = view.findViewById(R.id.tab_layoutAddPatient);
        layout_back = view.findViewById(R.id.ll_back);
        layout_homeBtn = view.findViewById(R.id.Rl_homeBtn);
        layout_menu = view.findViewById(R.id.ll_menu);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Dairy Products");

      /*  //method that contains click listener implementation
        clickListeners();
*/
        //this is used to store memory of tabs that aren't active on screen
        //store memory means if user swipes from one tab to another it will store the filled state of fields
        //limit 5 means 5 tabs next to the current one
        viewPager.setOffscreenPageLimit(5);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getParentFragmentManager());

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

        return view;


    }
    
   /* public void clickListeners() {

        //redirects user on homepage when back imageview is clicked from add patient activity
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //opens menu from add patient which contains
        // change password settings and logout options
        layout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                *//*   Method for Customize menu*//*
                dialogHandler.showPopupWindow(AddPatientActivity.this, "AddPatient");
            }
        });

        //redirects user to home when home button is clicked from add patient activity
        layout_homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPatientActivity.this, MainActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //opens notification activity when notification is clicked
        layout_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddPatientActivity.this, NotificationsActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //opens wallet activity when wallet is clicked
        layout_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddPatientActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });*/

}