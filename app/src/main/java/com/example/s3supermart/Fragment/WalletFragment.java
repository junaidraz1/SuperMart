package com.example.s3supermart.Fragment;

import static com.example.s3supermart.Activity.HomeActivity.currentFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.R;

public class WalletFragment extends Fragment {

    LinearLayout ll_back, ll_menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);






        // Inflate the layout for this fragment
        return view;
    }
}