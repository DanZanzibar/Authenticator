package com.example.authenticator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AuthenticatorUtils {

    public static String getCurrentPasscode() {
        Calendar calendar = Calendar.getInstance();
        int currentMinute = calendar.get(Calendar.MINUTE);
        int passcode = currentMinute * 1245 + 10000;
        return String.valueOf(passcode);
    }

    public static String getCurrentTimeStamp() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return simpleDateFormat.format(currentDate);
    }

    public static int getRemainingSecondsUntilTimeTick() {
        Calendar calendar = Calendar.getInstance();
        return 60 - calendar.get(Calendar.SECOND);
    }
}
