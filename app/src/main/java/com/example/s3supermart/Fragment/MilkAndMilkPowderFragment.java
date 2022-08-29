package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Adapter.ProductsAdapter;
import com.example.s3supermart.Model.ProductsClass;
import com.example.s3supermart.R;

import java.util.ArrayList;
import java.util.List;


public class MilkAndMilkPowderFragment extends Fragment {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<ProductsClass> productsClassList;
    TextView tv_topBar;

    public MilkAndMilkPowderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_milk_and_milk_powder, container, false);

        recyclerView = view.findViewById(R.id.rv_milkAndMilkPowder);
        recyclerView.setHasFixedSize(true);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("MilkAndMilkPowder");
        ((HomeActivity) getContext()).topBarWithBackIcon();
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        productsClassList = new ArrayList<>();

        //Adding Data into ArrayList
        productsClassList.add(new ProductsClass("Olpers Full Cream Milk ","250 ml","Rs.55"));
        productsClassList.add(new ProductsClass("Olpers Full Cream Milk ","1 Litre","Rs.200"));
        productsClassList.add(new ProductsClass("Olpers Full Cream Milk ","1,5 Litre","Rs.300"));
        productsAdapter = new ProductsAdapter(getContext(), productsClassList);

        recyclerView.setAdapter(productsAdapter);

        return view;
    }
}