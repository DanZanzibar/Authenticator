//******************************************************************************
//  AuthenticatorUtils.java
//
//  Zan Owsley T00745703
//  COMP 2161 
//  This class provides some static utility methods for the app.
//******************************************************************************
package com.example.authenticator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AuthenticatorUtils {

    //--------------------------------------------------------------------------
    //  This method generates a pass code based on the minutes on the clock.
    //--------------------------------------------------------------------------
    public static String getCurrentPasscode() {
        Calendar calendar = Calendar.getInstance();
        int currentMinute = calendar.get(Calendar.MINUTE);
        int passcode = currentMinute * 1245 + 10000;
        return String.valueOf(passcode);
    }

    //--------------------------------------------------------------------------
    //  This method generates a time stamp for the current time.
    //--------------------------------------------------------------------------
    public static String getCurrentTimeStamp() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return simpleDateFormat.format(currentDate);
    }

    //--------------------------------------------------------------------------
    //  This method returns the number of seconds until the next minute tick.
    //--------------------------------------------------------------------------
    public static int getRemainingSecondsUntilTimeTick() {
        Calendar calendar = Calendar.getInstance();
        return 60 - calendar.get(Calendar.SECOND);
    }
}
