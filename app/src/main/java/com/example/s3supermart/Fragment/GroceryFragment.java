package com.example.s3supermart.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.s3supermart.Activity.ChangePasswordActivity;
import com.example.s3supermart.Activity.GoogleMapActivity;
import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.R;

public class GroceryFragment extends Fragment {


    CardView cv_dairyProduct;
    TextView tv_topBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery, container, false);

        cv_dairyProduct = view.findViewById(R.id.cv_milkCheeseAndYogurt);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Grocery");
        ((HomeActivity) getContext()).topBarWithBackIcon();

        cv_dairyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null) {
                    Fragment fragment3 = new MilkCheeseAndYogurtFragment();
                    ((HomeActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3,
                            fragment3.getClass().getSimpleName()).addToBackStack(null).commit();
                }
/*
                startActivity(new Intent(getContext(), GoogleMapActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));*/
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}