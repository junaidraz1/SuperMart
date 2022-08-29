package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.s3supermart.R;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

public class CheckOutActivity extends AppCompatActivity {
    StateProgressBar stateProgressBar;
    String[] descriptionData = {"Delivery\nAddress", "Delivery\nDate|Time", "Payment\nMethod", "Place\nOrder"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        stateProgressBar = findViewById(R.id.stepsProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            @Override
            public void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState) {
                Toast.makeText(CheckOutActivity.this, "state Clicked Number is " + stateNumber, Toast.LENGTH_LONG).show();
            }
        });
    }
}