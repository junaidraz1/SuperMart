package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.R;

public class SearchFragment extends Fragment {

    TextView tv_topBar;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Search");
        ((HomeActivity) getContext()).topBarWithBackIcon();
        return view;
    }
}