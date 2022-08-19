package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.R;

public class GroceryFragment extends Fragment {


    CardView cv_dairyProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery, container, false);

        cv_dairyProduct = view.findViewById(R.id.cv_milkCheeseAndYogurt);


        cv_dairyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null) {
                    Fragment fragment3 = new MilkCheeseAndYogurtFragment();
                    ((HomeActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3,
                            fragment3.getClass().getSimpleName()).commit();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}