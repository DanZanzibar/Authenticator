//******************************************************************************
//  AuthenticatorSharedPreferences.java
//
//  Zan Owsley T00745703
//  COMP 2161 
//  This class facilitates access to the stored data in SharedPreferences.
//******************************************************************************
package com.example.authenticator;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthenticatorSharedPreferences {

    private static final String FILE_NAME = "com.example.authenticator.shared_preferences";
    private static final String PREF_NAME = "log_stamps";
    private final SharedPreferences sharedPreferences;

    //--------------------------------------------------------------------------
    //  A constructor for the class.
    //--------------------------------------------------------------------------
    public AuthenticatorSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    //--------------------------------------------------------------------------
    //  This method adds a new time stamp to the stored stamps.
    //--------------------------------------------------------------------------
    public void addTimeStamp(String timeStamp) {
        String currentLog = getLog();
        setLog(currentLog + "\n" + timeStamp);
    }

    //--------------------------------------------------------------------------
    //  This method clears all the time stamps except the most recent one.
    //--------------------------------------------------------------------------
    public void clearAllButLastTimeStamp() {
        String currentLog = getLog();
        String newLog;

        if (currentLog.contains("\n")) {
            String[] currentLogArray = currentLog.split("\n");
            newLog = currentLogArray[currentLogArray.length - 1];
        }
        else
            newLog = currentLog;

        setLog(newLog);
    }

    //--------------------------------------------------------------------------
    //  This method returns the String containing all the time stamps.
    //--------------------------------------------------------------------------
    public String getLog() {
        return sharedPreferences.getString(PREF_NAME, "");
    }

    //--------------------------------------------------------------------------
    //  This method sets the String that contains all the time stamps.
    //--------------------------------------------------------------------------
    private void setLog(String log) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, log);
        editor.apply();
    }
}
