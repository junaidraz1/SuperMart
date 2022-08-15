package com.example.s3supermart.Utils;

import java.util.Calendar;

public class Utility {

    public String getWishingMessage() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            return "Good Morning!";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            return "Good Afternoon!";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            return "Good Evening!";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            return "Good Night!";
        }

        return null;
    }


}
