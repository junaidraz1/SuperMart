package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.s3supermart.R;

public class ProfileFragment extends Fragment {

    LinearLayout layout_userInfo, layout_edituserInfo;
    Button btn_editProfile, btn_updateProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        layout_userInfo = view.findViewById(R.id.ll_basicInfoProfile);
        layout_edituserInfo = view.findViewById(R.id.ll_changeInfoProfile);
        btn_editProfile = view.findViewById(R.id.btn_editInfoProfile);
        btn_updateProfile = view.findViewById(R.id.btn_updateInfoProfile);

        clickListeners();

        return view;
    }

    public void clickListeners() {

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_edituserInfo.setVisibility(View.VISIBLE);
                layout_userInfo.setVisibility(View.GONE);
                btn_editProfile.setVisibility(View.GONE);
                btn_updateProfile.setVisibility(View.VISIBLE);

            }
        });

        btn_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_edituserInfo.setVisibility(View.GONE);
                layout_userInfo.setVisibility(View.VISIBLE);
                btn_editProfile.setVisibility(View.VISIBLE);
                btn_updateProfile.setVisibility(View.GONE);

            }
        });

    }
}