package com.example.s3supermart.Helper;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.s3supermart.Activity.HomeActivity;

public class AsyncTaskRunner extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {

        String resp = "";

        try {
            HomeActivity homeActivity = new HomeActivity();
            homeActivity.loadFrag();
            homeActivity.clickListeners();
            resp = "Response Loading ";
            Log.d("AsyncHelperClass", "doInBackground: " + resp);

        } catch (Exception e) {
            resp = "Response not Loaded ";
            Log.d("AsyncHelperClass", "exception: " + resp + e.getLocalizedMessage());
        }

        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }
}
