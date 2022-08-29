package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.s3supermart.R;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

public class CheckOutFragment extends Fragment {
    StateProgressBar stateProgressBar;
    String[] descriptionData = {"Delivery\nAddress", "Delivery\nDate|Time", "Payment\nMethod", "Place\nOrder"};
    public CheckOutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out, container, false);
        stateProgressBar = view.findViewById(R.id.stepsProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            @Override
            public void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState) {
                Toast.makeText(getContext(), "state Clicked Number is " + stateNumber, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}