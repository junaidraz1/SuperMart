package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Adapter.CheckOuutAdapter;
import com.example.s3supermart.Adapter.ProductsAdapter;
import com.example.s3supermart.Model.CheckOutClass;
import com.example.s3supermart.Model.ProductsClass;
import com.example.s3supermart.R;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFragment extends Fragment {

    LinearLayout ll_deliveryDetails, ll_dateTime, ll_paymentMethod, ll_placeOrder;
    Button btn_continueDeliveryDetails, btn_continueDateTime, btn_continuepaymentMethod, btn_placeOrder;
    StateProgressBar stateProgressBar;
    RecyclerView recyclerView;
    CheckOuutAdapter checkOuutAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<CheckOutClass> checkOutClasses;
    TextView tv_topBar;
    //String[] descriptionData = {"Delivery\nAddress", "Delivery\nDate|Time", "Payment\nMethod", "Place\nOrder"};
    // String[] descriptionData = {"1", "2", "3", "4"};

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
        btn_placeOrder = view.findViewById(R.id.btn_placeOrder);
        ll_deliveryDetails = view.findViewById(R.id.ll_deliveryDetails);
        ll_dateTime = view.findViewById(R.id.ll_deliveryDateAndTime);
        ll_paymentMethod = view.findViewById(R.id.ll_paymentMethod);
        ll_placeOrder = view.findViewById(R.id.ll_placeOrder);
        stateProgressBar = view.findViewById(R.id.stepsProgressBar);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("Check Out");

        if (getContext() != null) {
            ((HomeActivity) getContext()).topBarWithBackIcon();
        }
        //   stateProgressBar.setStateDescriptionData(descriptionData);
        recyclerView = view.findViewById(R.id.rv_checkOut_Products);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        checkOutClasses = new ArrayList<>();

        //Adding Data into ArrayList
        checkOutClasses.add(new CheckOutClass("Olpers Full Cream Milk ", "2", "Rs.55"));
        checkOutClasses.add(new CheckOutClass("Olpers Full Cream Milk ", "1 ", "Rs.200"));
        checkOutClasses.add(new CheckOutClass("Olpers Full Cream Milk ", "1", "Rs.300"));
        checkOuutAdapter = new CheckOuutAdapter(getContext(), checkOutClasses);

        recyclerView.setAdapter(checkOuutAdapter);

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
                ll_placeOrder.setVisibility(View.GONE);
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
                ll_placeOrder.setVisibility(View.GONE);
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
                ll_placeOrder.setVisibility(View.VISIBLE);
                ll_dateTime.setVisibility(View.GONE);
            }
        });

        btn_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_deliveryDetails.setVisibility(View.GONE);
                ll_paymentMethod.setVisibility(View.GONE);
                ll_placeOrder.setVisibility(View.VISIBLE);
                ll_dateTime.setVisibility(View.GONE);
            }
        });

        return view;

    }
}