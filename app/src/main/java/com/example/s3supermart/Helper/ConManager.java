package com.example.s3supermart.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.s3supermart.Activity.GoogleMapActivity;

public class ConManager {

    public boolean checkConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            connected = true;
            return connected;
        } else {
            // Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            DialogHandler.customDialog(context, "No Internet Connection", "Pllease check your internet connection and try again");
            return connected;
        }
    }
}
