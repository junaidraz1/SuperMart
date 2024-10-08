package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.R;

public class WalletFragment extends Fragment {

    LinearLayout ll_back, ll_menu;
    TextView tv_topBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Wallet");
        ((HomeActivity) getContext()).topBarWithBackIcon();


        // Inflate the layout for this fragment
        return view;
    }
}