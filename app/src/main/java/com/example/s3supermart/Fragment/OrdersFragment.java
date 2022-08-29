package com.example.s3supermart.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Adapter.ViewPagerAdapter;
import com.example.s3supermart.R;
import com.google.android.material.tabs.TabLayout;

public class OrdersFragment extends Fragment {
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tv_topBar;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        //intialising ids to  variables
        viewPager = view.findViewById(R.id.viewpagerOrders);
        tabLayout = view.findViewById(R.id.tab_layoutOrders);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Orders");
        ((HomeActivity) getContext()).topBarWithBackIcon();
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
        viewPagerAdapter.add(new CurrentOrderFragment(), "Current");
        viewPagerAdapter.add(new HistoryOrderFragment(), "History");
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
}