package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.s3supermart.R;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

public class CheckOutFragment extends Fragment {

    LinearLayout ll_deliveryDetails, ll_dateTime, ll_paymentMethod, ll_placeOrder;
    Button btn_continueDeliveryDetails, btn_continueDateTime, btn_continuepaymentMethod, btn_continueplaceOrder;
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
        btn_continueDeliveryDetails = view.findViewById(R.id.btn_nextDeliveryDetails);
        btn_continueDateTime = view.findViewById(R.id.btn_nextDateTime);
        btn_continuepaymentMethod = view.findViewById(R.id.btn_nextPaymentMethod);
        ll_deliveryDetails = view.findViewById(R.id.ll_deliveryDetails);
        ll_dateTime = view.findViewById(R.id.ll_deliveryDateAndTime);
        ll_paymentMethod = view.findViewById(R.id.ll_paymentMethod);
        stateProgressBar = view.findViewById(R.id.stepsProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            @Override
            public void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState) {
                Toast.makeText(getContext(), "state Clicked Number is " + stateNumber, Toast.LENGTH_LONG).show();
            }
        });

        btn_continueDeliveryDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.enableAnimationToCurrentState(true);
                stateProgressBar.setAnimationDuration(15);
                ll_deliveryDetails.setVisibility(View.GONE);
                ll_dateTime.setVisibility(View.VISIBLE);
                ll_paymentMethod.setVisibility(View.GONE);
            }
        });

        btn_continueDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                stateProgressBar.enableAnimationToCurrentState(true);
                ll_deliveryDetails.setVisibility(View.GONE);
                ll_paymentMethod.setVisibility(View.VISIBLE);
                ll_dateTime.setVisibility(View.GONE);
            }
        });

        btn_continuepaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                stateProgressBar.enableAnimationToCurrentState(true);
                ll_deliveryDetails.setVisibility(View.GONE);
                ll_paymentMethod.setVisibility(View.GONE);
                ll_dateTime.setVisibility(View.GONE);
            }
        });

        return view;
    }
}